package com.xh.apipassenger.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.TokenResult;
import com.xg.internalcommon.request.VerificationCodeDTO;
import com.xg.internalcommon.utils.JwtUtils;
import com.xh.apipassenger.remote.ServicePassengerUserClient;
import com.xh.apipassenger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Override
    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken: " + accessToken);
        //解析accessToken 拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("Phone Number:" + phone);
        ResponseResult userByPhone = servicePassengerUserClient.getUserByPhone(phone);
        return ResponseResult.success(userByPhone.getData());
    }
}
