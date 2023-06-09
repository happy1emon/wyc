package com.xg.apipassenger.service.impl;

import com.xg.apipassenger.remote.ServiceOrderClient;
import com.xg.apipassenger.service.OrderService;
import com.xg.internalcommon.constant.IdentityConstant;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/4/5
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ServiceOrderClient serviceOrderClient;


    @Override
    public ResponseResult add(OrderRequest orderRequest) {
        return serviceOrderClient.add(orderRequest);
    }

    @Override
    public String testClucherOrder(Long orderId) {
        return serviceOrderClient.testClucherOrder(orderId);
    }

    @Override
    public ResponseResult cancel(Long orderId) {
        return serviceOrderClient.cancel(orderId, IdentityConstant.PASSENGER_IDENTITY);
    }
}
