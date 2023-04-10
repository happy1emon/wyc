package com.xg.internalcommon.constant;


import lombok.Data;
import lombok.Getter;

//定义枚举类

public enum CommonStatusEnum {

    ORDER_IS_STARTING(1600,"已有正在进行的订单"),
    /**
     * 下单异常
     */
    DEVICE_IS_BLACK(1601,"该设备超过下单次数"),
    CITY_SERVICE_INAVAILABLE(1602,"该城市不提供该服务"),


    DRIVER_CAR_BIND_NOT_EXISTS(1500,"司机和车辆绑定关系不存在"),
    DRIVER_NOT_EXISTS(1501,"司机不存在"),
    DRIVER_CAR_BIND_EXISTS(1502,"司机和车辆绑定关系已经存在"),
    DRIVER_HAS_BOUND_ERORR(1503,"该司机已有绑定车辆"),
    CAR_HAS_BOUND_ERORR(1504,"车辆已被绑定过"),
    DRIVER_CAR_BIND_NOT_EXIST(1505,"绑定关系不存在"),
    NO_AVAILABLE_DRIVER(1506,"当前城市没有可用司机"),



    MAP_DISTRICT_ERRO(1400,"请求地图错误"),

    /**
     * price rule empty code in [1300,1399]
     */
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),
    PRICE_RULE_EXISTS(1301,"计价规则已经存在"),
    PRICE_RULE_NOT_EDIT(1302,"计价规则没有变化"),
    PRICE_RULE_NOT_SAME(1303,"当前不是最新计价规则"),


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
    FAIL(0,"fail"),
    USER_NOT_EXISTS(1200, "当前用户不存在"),
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
