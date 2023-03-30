package com.xg.apidriver.service.impl;

import com.xg.apidriver.service.VerificationCodeService;
import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Override
    public ResponseResult checkAndSendVerificationCode(String driverPhone) {
        //1 查询手机号是否存在


        //2 获取验证码


        //3 调用第三方发送验证码


        //4 存入redis

        return ResponseResult.success("");
    }
}
