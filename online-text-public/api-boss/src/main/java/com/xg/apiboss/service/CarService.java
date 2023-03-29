package com.xg.apiboss.service;

import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.ResponseResult;

/**
 * @USER: XGGG
 * @DATE: 2023/3/29
 */
public interface CarService {
    ResponseResult addCar(Car car);
}
