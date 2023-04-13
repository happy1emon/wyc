package com.xg.apidriver.service.impl;

import com.xg.apidriver.remote.ServiceSsePushClient;
import com.xg.apidriver.service.PayService;
import com.xg.internalcommon.constant.IdentityConstant;
import com.xg.internalcommon.dto.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/4/13
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private ServiceSsePushClient serviceSsePushClient;


    @Override
    public ResponseResult pushPayInfo(Long orderId, Double price ,Long passengerId) {
        //封装消息
        JSONObject message=new JSONObject();
        message.put("price",price);
        //推送消息给乘客端
        serviceSsePushClient.push(passengerId, IdentityConstant.PASSENGER_IDENTITY,message.toString());




        return null;
    }
}
