package com.xg.apiboss.service.impl;

import com.xg.apiboss.remote.ServiceDriverUserClient;
import com.xg.apiboss.service.DriverUserService;
import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @USER: XGGG
 * @DATE: 2023/3/28
 */
@Service
public class DriverUserServiceImpl implements DriverUserService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Override
    public ResponseResult addDriverUser(DriverUser driverUser) {
        return serviceDriverUserClient.addDriverUser(driverUser);
    }

    @Override
    public ResponseResult updateDriverUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateDriverUser(driverUser);
    }
}
