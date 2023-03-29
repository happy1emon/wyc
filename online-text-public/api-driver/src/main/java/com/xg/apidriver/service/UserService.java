package com.xg.apidriver.service;

import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.dto.ResponseResult;

/**
 * @USER: XGGG
 * @DATE: 2023/3/29
 */
public interface UserService {

    ResponseResult updateUser(DriverUser driverUser);
}
