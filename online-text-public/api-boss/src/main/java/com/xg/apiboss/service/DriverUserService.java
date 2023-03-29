package com.xg.apiboss.service;

import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.dto.ResponseResult;

import java.sql.Driver;

/**
 * @USER: XGGG
 * @DATE: 2023/3/28
 */
public interface DriverUserService {

    ResponseResult addDriverUser(DriverUser driverUser);
    ResponseResult updateDriverUser(DriverUser driverUser);

}
