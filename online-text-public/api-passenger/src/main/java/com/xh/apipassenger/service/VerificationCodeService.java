package com.xh.apipassenger.service;


import com.xg.internalcommon.dto.ResponseResult;

public interface VerificationCodeService {

    /**
     * 生成随机验证码
     * @param passengerPhone 手机号
     * @return 验证码
     */
    ResponseResult generatorCode(String passengerPhone);

    ResponseResult checkVerificationCode(String passengerPhone,String verificationCode);
}
