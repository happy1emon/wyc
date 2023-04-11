package com.xg.serviceorder.service.impl;

import com.alibaba.nacos.api.config.filter.IFilterConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.OrderConstants;
import com.xg.internalcommon.dto.OrderInfo;
import com.xg.internalcommon.dto.PriceRule;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import com.xg.internalcommon.response.OrderDriverResponse;
import com.xg.internalcommon.response.TerminalResponse;
import com.xg.internalcommon.utils.RedisPrefixUtils;
import com.xg.serviceorder.remote.ServiceDriverUserClient;
import com.xg.serviceorder.remote.ServiceMapClient;
import com.xg.serviceorder.remote.ServicePriceClient;
import com.xg.serviceorder.service.OrderInfoService;
import com.xg.serviceorder.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author junxuan
 * @description 针对表【order_info】的数据库操作Service实现
 * @createDate 2023-04-06 13:35:18
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
        implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private ServicePriceClient servicePriceClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Override
    @Transactional
    public ResponseResult add(OrderRequest orderRequest) {

        //判断该地区和车型是否有对应计价规则
        Boolean data = ifExists(orderRequest);
        if (!data)
            return ResponseResult.fail(CommonStatusEnum.CITY_SERVICE_INAVAILABLE.getCode(), CommonStatusEnum.CITY_SERVICE_INAVAILABLE.getValue());

        //判断该地区有没有可用司机
        //这里还是不要判断工作状态
        //存在一种情况就是这里查的时候是有工作的 但是到后面下单的过程中他收车了
        //这样用户在这边通过了但是在后面又没通过 所以还是在后面找司机的时候判断
        ResponseResult<Boolean> availableDriver = serviceDriverUserClient.isAvailableDriver(orderRequest.getAddress());
        Boolean isAvailable = availableDriver.getData();
        if (!isAvailable) {
            return ResponseResult.fail(CommonStatusEnum.NO_AVAILABLE_DRIVER.getCode(), CommonStatusEnum.NO_AVAILABLE_DRIVER.getValue());
        }

        //在下单前判断版本是否是最新
        ResponseResult<Boolean> isNew = servicePriceClient.isNew(orderRequest.getFareType(), orderRequest.getFareVersion());
        Boolean result = isNew.getData();
        if (!result) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_NOT_SAME.getCode(), CommonStatusEnum.PRICE_RULE_NOT_SAME.getValue());
        }

        //判断该乘客是否已经有订单正在执行
        List<Integer> valid = orderInfoMapper.isValid(orderRequest.getPassengerId());
        if (valid.size() != 0) {
            Integer orderStatus = valid.get(0);
            if (!(orderStatus.intValue() != OrderConstants.ORDER_INVALID || orderStatus.intValue() != OrderConstants.ORDER_CANCEL)) {
                return ResponseResult.fail(CommonStatusEnum.ORDER_IS_STARTING.getCode(), CommonStatusEnum.ORDER_IS_STARTING.getValue());
            }
        }

        //需要判断 下单的设备是否是黑名单设备 这里是防止短时间内刷单
        String deviceCode = orderRequest.getDeviceCode();
        //生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefix + deviceCode;
        //设置key，看原来有没有key
        if (isBlackDevice(deviceCodeKey))
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());

        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest, orderInfo);
        //设置状态
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        //设置时间
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);
        orderInfoMapper.insert(orderInfo);

        Boolean flag = dispatchRealTimeOrder(orderInfo);
        if (!flag) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_CREATE_FAIL.getCode(), CommonStatusEnum.ORDER_CREATE_FAIL.getValue());
        }
        return ResponseResult.success("");
    }

    private Boolean ifExists(OrderRequest orderRequest) {
        PriceRule priceRule = new PriceRule();
        int index = orderRequest.getFareType().indexOf("$");
        String cityCode = orderRequest.getFareType().substring(0, index);
        String vehicleType = orderRequest.getFareType().substring(index + 1);
        priceRule.setCityCode(cityCode);
        priceRule.setVehicleType(vehicleType);
        ResponseResult<Boolean> booleanResponseResult = servicePriceClient.ifExists(priceRule);
        return booleanResponseResult.getData();
    }

    private boolean isBlackDevice(String deviceCodeKey) {
        Boolean aBoolean = stringRedisTemplate.hasKey(deviceCodeKey);
        if (aBoolean) {
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if (i >= 2) {
                //当前设备超过下单次数
                return true;
            } else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1l, TimeUnit.HOURS);
        }
        return false;
    }

    /**
     * 实时订单派单逻辑
     *
     * @param orderInfo orderInfo
     */
    public Boolean dispatchRealTimeOrder(OrderInfo orderInfo) {
        String depLongitude = orderInfo.getDepLongitude();
        String depLatitude = orderInfo.getDepLatitude();
        String center = depLatitude + "," + depLongitude;
        ArrayList<Integer> radiusList = new ArrayList<>();
        ResponseResult<ArrayList<TerminalResponse>> listResponseResult = null;
        //添加搜索半径
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);
        for (int i = 0; i < radiusList.size(); i++) {
            listResponseResult = serviceMapClient.aroundSearch(center, String.valueOf(radiusList.get(i)));
            //获得终端
            ArrayList<TerminalResponse> data = listResponseResult.getData();
            if (data.size() == 0) {
                continue;
            }
            //解析终端
            //根据解析出来的终端，查询车辆信息
            JSONArray results = JSONArray.fromObject(data);
            for (int j = 0; j < data.size(); j++) {
                JSONObject jsonObject = results.getJSONObject(j);
                Long carId = jsonObject.getLong("desc");
                //查询司机是否是出车状态
                ResponseResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                if (availableDriver.getCode() == CommonStatusEnum.NO_AVAILABLE_DRIVER.getCode()) {
                    continue;
                }
                OrderDriverResponse driverResponse = availableDriver.getData();
                Long driverId = driverResponse.getDriverId();
                Integer orderStatus = orderInfoMapper.isOrderGoingOnByDriverId(driverId);
                if (orderStatus != null) {
                    if (orderStatus.intValue() != OrderConstants.ORDER_INVALID || orderStatus.intValue() != OrderConstants.OVER_PAY) {
                        continue;
                    }
                }
                // 且没有订单的司机
                // 找到符合的车辆进行派单
                UpdateWrapper<OrderInfo> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", orderInfo.getId());
                updateWrapper.set("driver_id", driverId);
                updateWrapper.set("car_id", carId);
                updateWrapper.set("driver_phone", driverResponse.getDriverPhone());
                int update = orderInfoMapper.update(null, updateWrapper);
                //派单成功 退出循环
                if (update == 1) {
                    return true;
                }
            }
        }
        UpdateWrapper<OrderInfo> uw = new UpdateWrapper<>();
        uw.eq("id", orderInfo.getId());
        uw.set("order_status", OrderConstants.ORDER_INVALID);
        //如果没找到 则订单无效
        int update = orderInfoMapper.update(null, uw);
        return false;
    }
}




