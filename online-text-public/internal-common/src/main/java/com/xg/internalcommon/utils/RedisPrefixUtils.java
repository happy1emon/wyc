package com.xg.internalcommon.utils;

public class RedisPrefixUtils {
    //乘客验证码前缀
    public static final String verificationCodePrefix = "verification-code-";
    //token前缀
    public static String tokenPrefix = "token-";

    /**
     * 根据身份和手机号 生成key
     *
     * @param Phone    手机号
     * @param identity 身份标识
     * @return 生成的Key 为了防止同一个人 既是乘客和又是司机 同时发验证码
     */
    public static String generatorKeyByPhone(String Phone, String identity) {
        return verificationCodePrefix + identity + "-" + Phone;
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
