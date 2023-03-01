package com.xh.apipassenger.service;



public interface VerificationCodeService {

    /**
     * 生成随机验证码
     * @param passengerPhone 手机号
     * @return 验证码
     */
    String generatorCode(String passengerPhone);
}
