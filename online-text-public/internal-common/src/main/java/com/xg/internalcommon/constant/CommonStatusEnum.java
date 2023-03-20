package com.xg.internalcommon.constant;


import lombok.Data;
import lombok.Getter;

//定义枚举类

public enum CommonStatusEnum {
    /**
     * price rule empty code in [1300,1399]
     */
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),

    /**
     * token error code in [1100,1199]
     */
    TOKEN_ERROR(1199,"token错误"),
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    /**
     * 成功
     */
    SUCCESS(1,"success"),
    /**
     * 失败
     */
    FAIL(0,"fail"), USER_NOT_EXISTS(1200, "当前用户不存在"),
    /**
     * code in [2000,2100]
     */
    PRICE_MULTIRULE_ERROR(2000,"计价规则复用" );
    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
