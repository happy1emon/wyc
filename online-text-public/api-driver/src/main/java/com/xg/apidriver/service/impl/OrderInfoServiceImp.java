package com.xg.apidriver.service.impl;

import com.xg.apidriver.remote.ServiceOrderClient;
import com.xg.apidriver.service.OrderInfoService;
import com.xg.internalcommon.constant.IdentityConstant;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/4/12
 */
@Service
public class OrderInfoServiceImp implements OrderInfoService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    @Override
    public ResponseResult toPickUpPassenger(OrderRequest orderRequest) {
        return serviceOrderClient.toPickUpPassenger(orderRequest);
    }

    @Override
    public ResponseResult arrivedDeparture(OrderRequest orderRequest) {
        return serviceOrderClient.arrivedDeparture(orderRequest);
    }

    @Override
    public ResponseResult PickUpPassenger(OrderRequest orderRequest) {
        return serviceOrderClient.pickUpPassenger(orderRequest);
    }

    @Override
    public ResponseResult passengerGetoff(OrderRequest orderRequest) {
        return serviceOrderClient.passengerGetoff(orderRequest);
    }

    @Override
    public ResponseResult cancel(Long orderId) {
        return serviceOrderClient.cancel(orderId, IdentityConstant.DRIVER_IDENTITY);
    }
}
