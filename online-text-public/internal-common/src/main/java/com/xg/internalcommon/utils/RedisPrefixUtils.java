package com.xg.internalcommon.utils;

public class RedisPrefixUtils {
    //乘客验证码前缀
    public static final String verificationCodePrefix = "passenger-verification-code-";
    //token前缀
    public static String tokenPrefix = "token-";

    public static String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份标识，生成token
     *
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone, String identity, String tokenType) {
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
