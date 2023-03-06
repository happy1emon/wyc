package com.xg.servicepassengeruser.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicepassengeruser.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("user service");
        //根据手机号查询用户信息

        //判断用户信息是否存在

        //如果不存在，插入用户信息

        return ResponseResult.success();
    }
}
