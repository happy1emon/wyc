package com.xg.serviceorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.scenario.effect.Identity;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.IdentityConstant;
import com.xg.internalcommon.constant.OrderConstants;
import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.OrderInfo;
import com.xg.internalcommon.dto.PriceRule;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import com.xg.internalcommon.response.OrderDriverResponse;
import com.xg.internalcommon.response.TerminalResponse;
import com.xg.internalcommon.response.TrsearchResponse;
import com.xg.internalcommon.utils.RedisPrefixUtils;
import com.xg.serviceorder.remote.ServiceDriverUserClient;
import com.xg.serviceorder.remote.ServiceMapClient;
import com.xg.serviceorder.remote.ServicePriceClient;
import com.xg.serviceorder.remote.ServiceSsePushClient;
import com.xg.serviceorder.service.OrderInfoService;
import com.xg.serviceorder.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.tomcat.jni.Local;
import org.aspectj.weaver.ast.Or;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.JobSheets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ServiceSsePushClient serviceSsePushClient;

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
            if (orderStatus.intValue() != OrderConstants.ORDER_INVALID && orderStatus.intValue() != OrderConstants.ORDER_CANCEL) {
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
        Boolean flag = false;
        //循环查找 每20s找一轮 循环5轮
        for (int i = 0; i < 6; i++) {
            flag = dispatchRealTimeOrder(orderInfo);
            if (flag)
                break;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!flag) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_CREATE_FAIL.getCode(), CommonStatusEnum.ORDER_CREATE_FAIL.getValue());
        }
        return ResponseResult.success("下单成功");
    }

    @Override
    /**
     * 实时订单派单逻辑
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
            for (int j = 0; j < data.size(); j++) {
                TerminalResponse terminalResponse = data.get(j);
                Long carId = terminalResponse.getDesc();
                //查询司机是否是出车状态
                ResponseResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                //判断司机是否有订单
                if (availableDriver.getCode() == CommonStatusEnum.NO_AVAILABLE_DRIVER.getCode()) {
                    continue;
                }

                OrderDriverResponse driverResponse = availableDriver.getData();
                Long driverId = driverResponse.getDriverId();
                //锁司机id小技巧 变成字符串之后放到常量池里
                // .intern()防止一直创造新的String对象造成锁的不是一个数据
                //用redis来锁
                String lockKey = (driverId + "").intern();
                RLock lock = redissonClient.getLock(lockKey);
                lock.lock();
                // synchronized ((driverId+"").intern()){
                List<Integer> orderStatus = orderInfoMapper.isOrderGoingOnByDriverId(driverId);
                if (orderStatus.get(0) > 0) {
                    //这里没有unlock 会造成死锁
                    lock.unlock();
                    continue;
                }
                // 且没有订单的司机
                // 找到符合的车辆进行派单 先不管司机接不接 派就完了
                orderInfo.setReceiveOrderTime(LocalDateTime.now());
                orderInfo.setReceiveOrderCarLongitude(terminalResponse.getLongitude());
                orderInfo.setReceiveOrderCarLatitude(terminalResponse.getLatitude());
                orderInfo.setCarId(carId);
                orderInfo.setDriverId(driverId);
                orderInfo.setLicenseId(driverResponse.getLicenseId());
                orderInfo.setDriverPhone(driverResponse.getDriverPhone());
                orderInfo.setVehicleNo(driverResponse.getVehicleNo());
                orderInfo.setOrderStatus(OrderConstants.DRIVER_RECEIVE_ORDER);
                orderInfo.setGmtModified(LocalDateTime.now());
                UpdateWrapper<OrderInfo> orderInfoUpdateWrapper = new UpdateWrapper<>();
                orderInfoUpdateWrapper.eq("id", orderInfo.getId());
                int update = orderInfoMapper.update(orderInfo, orderInfoUpdateWrapper);
                //通知司机
                //通知信息拼接
                JSONObject driverContent = generatePushDriverContent(orderInfo);
                JSONObject passengerContent = generatePushPassengerContent(orderInfo);
                serviceSsePushClient.push(driverId, IdentityConstant.DRIVER_IDENTITY, driverContent.toString());
                serviceSsePushClient.push(orderInfo.getPassengerId(), IdentityConstant.PASSENGER_IDENTITY, passengerContent.toString());
                lock.unlock();
                //派单成功 退出循环
                if (update >= 1) {
                    return true;
                }
            }
        }
        UpdateWrapper<OrderInfo> uw = new UpdateWrapper<>();
        uw.eq("id", orderInfo.getId());
        uw.set("order_status", OrderConstants.ORDER_START);
        //如果没找到 则订单无效
        int update = orderInfoMapper.update(null, uw);
        return false;
    }

    @Override
    @Transactional
    public ResponseResult toPickUpPassenger(OrderRequest orderRequest) {
        System.out.println(orderRequest);
        Long orderId = orderRequest.getOrderId();
        LocalDateTime toPickUpPassengerTime = orderRequest.getToPickUpPassengerTime();
        String toPickUpPassengerAddress = orderRequest.getToPickUpPassengerAddress();
        String toPickUpPassengerLongitude = orderRequest.getToPickUpPassengerLongitude();
        String toPickUpPassengerLatitude = orderRequest.getToPickUpPassengerLatitude();
        QueryWrapper<OrderInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);
        orderInfo.setToPickUpPassengerAddress(toPickUpPassengerAddress);
        orderInfo.setToPickUpPassengerLongitude(toPickUpPassengerLongitude);
        orderInfo.setToPickUpPassengerLatitude(toPickUpPassengerLatitude);
        orderInfo.setToPickUpPassengerTime(toPickUpPassengerTime);
        orderInfo.setOrderStatus(OrderConstants.DRIVER_TO_PICK_UP_PASSENGER);
        orderInfo.setGmtModified(LocalDateTime.now());
        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success("");
    }

    @Override
    @Transactional
    public ResponseResult arrivedDeparture(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();
        QueryWrapper<OrderInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);
        orderInfo.setGmtModified(LocalDateTime.now());
        orderInfo.setOrderStatus(OrderConstants.DRIVER_ARRIVED_DEPARTURE);
        orderInfo.setDriverArrivedDepartureTime(LocalDateTime.now());
        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success("");
    }

    @Override
    @Transactional
    public ResponseResult PickUpPassenger(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();
        QueryWrapper<OrderInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);
        orderInfo.setPickUpPassengerLongitude(orderRequest.getPickUpPassengerLongitude());
        orderInfo.setPickUpPassengerLatitude(orderRequest.getPickUpPassengerLatitude());
        orderInfo.setPickUpPassengerTime(LocalDateTime.now());
        orderInfo.setOrderStatus(OrderConstants.PICK_UP_PASSENGER);
        orderInfo.setGmtModified(LocalDateTime.now());
        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success("");
    }

    @Override
    @Transactional
    public ResponseResult passengerGetoff(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();
        QueryWrapper<OrderInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);
        orderInfo.setPassengerGetoffLongitude(orderRequest.getPassengerGetoffLongitude());
        orderInfo.setPassengerGetoffLatitude(orderRequest.getPassengerGetoffLatitude());
        orderInfo.setPassengerGetoffTime(LocalDateTime.now());
        orderInfo.setOrderStatus(OrderConstants.PASSENGER_GETOFF);
        orderInfo.setGmtModified(LocalDateTime.now());
        //缺少订单行驶的路程和时间
        //调用map服务获取行程的路程和时间
        Long carId = orderInfo.getCarId();
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(carId);
        Car data = carById.getData();
        String tid = data.getTid();
        long starttime = orderInfo.getPickUpPassengerTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long endtime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        ResponseResult<TrsearchResponse> trsearch = serviceMapClient.trsearch(tid, starttime, endtime);
        TrsearchResponse trsearchData = trsearch.getData();
        orderInfo.setDriveTime(trsearchData.getDriveTime());
        orderInfo.setDriveMile(trsearchData.getDriveMile());

        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success("");
    }

    private JSONObject generatePushDriverContent(OrderInfo orderInfo) {
        JSONObject driverContent = new JSONObject();
        driverContent.put("passengerPhone", orderInfo.getPassengerPhone());
        driverContent.put("passengerId", orderInfo.getPassengerId());
        driverContent.put("departure", orderInfo.getDeparture());
        driverContent.put("depLongitude", orderInfo.getDepLongitude());
        driverContent.put("depLatitude", orderInfo.getDepLatitude());
        driverContent.put("departTime", orderInfo.getDepartTime());
        driverContent.put("destination", orderInfo.getDestination());
        driverContent.put("destLongitude", orderInfo.getDestLongitude());
        driverContent.put("destLatitude", orderInfo.getDestLatitude());
        return driverContent;
    }

    private JSONObject generatePushPassengerContent(OrderInfo orderInfo) {
        JSONObject passengerContent = new JSONObject();
        passengerContent.put("driverId", orderInfo.getDriverId());
        passengerContent.put("driverPhone", orderInfo.getDriverPhone());
        passengerContent.put("vehicleNo", orderInfo.getVehicleNo());
        //待补充

        //到达时间

        //距离

        passengerContent.put("receiveOrderCarLongitude", orderInfo.getReceiveOrderCarLongitude());
        passengerContent.put("receiveOrderCarLatitude", orderInfo.getReceiveOrderCarLatitude());

        //汽车信息
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(orderInfo.getCarId());
        Car carData = carById.getData();
        passengerContent.put("brand", carData.getBrand());
        passengerContent.put("model", carData.getModel());
        passengerContent.put("color", carData.getVehicleColor());


        return passengerContent;
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


}




