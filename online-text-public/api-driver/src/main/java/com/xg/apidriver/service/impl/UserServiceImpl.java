package com.xg.apidriver.service.impl;

import com.xg.apidriver.remote.ServiceDriverUserClient;
import com.xg.apidriver.service.UserService;
import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.dto.ResponseResult;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/3/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Override
    public ResponseResult updateUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateUser(driverUser);
    }
}
