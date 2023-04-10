package com.xg.serviceorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.OrderConstants;
import com.xg.internalcommon.dto.OrderInfo;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import com.xg.internalcommon.utils.RedisPrefixUtils;
import com.xg.serviceorder.remote.ServicePriceClient;
import com.xg.serviceorder.service.OrderInfoService;
import com.xg.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author junxuan
* @description 针对表【order_info】的数据库操作Service实现
* @createDate 2023-04-06 13:35:18
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService{

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private ServicePriceClient servicePriceClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult add(OrderRequest orderRequest) {

        //在下单前判断版本是否是最新
        ResponseResult<Boolean> isNew = servicePriceClient.isNew(orderRequest.getFareType(), orderRequest.getFareVersion());
        Boolean result = isNew.getData();
        if (!result){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_NOT_SAME.getCode(),CommonStatusEnum.PRICE_RULE_NOT_SAME.getValue());
        }
        //需要判断 下单的设备是否是黑名单设备 这里是防止短时间内刷单
        String deviceCode = orderRequest.getDeviceCode();
        //生成key
        String deviceCodeKey= RedisPrefixUtils.blackDeviceCodePrefix+deviceCode;
        //设置key，看原来有没有key
        if (isBlackDevice(deviceCodeKey))
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());

        //判断该乘客是否已经有订单正在执行
        List<Integer> valid = orderInfoMapper.isValid(orderRequest.getPassengerId());
        Integer orderStatus = valid.get(0);
        if (orderStatus!=OrderConstants.ORDER_INVALID || orderStatus!=OrderConstants.ORDER_CANCEL){
            return ResponseResult.fail(CommonStatusEnum.ORDER_IS_STARTING.getCode(),CommonStatusEnum.ORDER_IS_STARTING.getValue());
        }
        OrderInfo orderInfo=new OrderInfo();
        BeanUtils.copyProperties(orderRequest,orderInfo);
        //设置状态
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        //设置时间
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);
        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success("");
    }

    private boolean isBlackDevice(String deviceCodeKey) {
        Boolean aBoolean = stringRedisTemplate.hasKey(deviceCodeKey);
        if (aBoolean){
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if (i>=2){
                //当前设备超过下单次数
                return true;
            }
            else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        }else
        {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey,"1",1l, TimeUnit.HOURS);
        }
        return false;
    }
}




