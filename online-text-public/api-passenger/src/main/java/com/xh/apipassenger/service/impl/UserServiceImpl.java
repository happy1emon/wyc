package com.xh.apipassenger.service.impl;

import com.xg.internalcommon.dto.PassengerUser;
import com.xg.internalcommon.dto.ResponseResult;
import com.xh.apipassenger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken: " + accessToken);
        //解析accessToken 拿到手机号
        PassengerUser passengerUser=new PassengerUser();
        //根据手机号拿去用户信息
        passengerUser.setPassengerName("Zhang San");
        passengerUser.setProfilePhoto("touxiang");
        return ResponseResult.success(passengerUser);
    }
}
