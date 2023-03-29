package com.xg.apiboss.service.impl;

import com.xg.apiboss.remote.ServiceDriverUserClient;
import com.xg.apiboss.service.CarService;
import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/3/29
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Override
    public ResponseResult addCar(Car car) {
        return serviceDriverUserClient.addCar(car);
    }
}
