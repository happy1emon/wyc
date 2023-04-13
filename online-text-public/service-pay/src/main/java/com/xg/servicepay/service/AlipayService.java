package com.xg.servicepay.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import com.xg.servicepay.remote.ServiceOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * @USER: XGGG
 * @DATE: 2023/4/13
 */
@Service
public class AlipayService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    public void pay(Long orderId){
        OrderRequest orderRequest=new OrderRequest();
        orderRequest.setOrderId(orderId);
        serviceOrderClient.pay(orderRequest);
    }

}
