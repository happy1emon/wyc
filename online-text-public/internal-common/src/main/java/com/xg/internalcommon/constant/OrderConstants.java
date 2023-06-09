package com.xg.internalcommon.constant;

/**
 * @USER: XGGG
 * @DATE: 2023/4/6
 */
public class OrderConstants {

    public static final int ORDER_INVALID = 0;
    /**
     * 订单开始
     */
    public static final int ORDER_START = 1;
    /**
     * 司机接单
     */
    public static final int DRIVER_RECEIVE_ORDER = 2;
    /**
     * 司机去接乘客
     */
    public static final int DRIVER_TO_PICK_UP_PASSENGER = 3;
    /**
     * 司机到达乘客地点
     */
    public static final int DRIVER_ARRIVED_DEPARTURE = 4;
    /**
     * 乘客上车 司机开始行程
     */
    public static final int PICK_UP_PASSENGER = 5;
    /**
     * 到达目的地 行程结束 未支付
     */
    public static final int PASSENGER_GETOFF = 6;
    /**
     * 发起收款
     */
    public static final int TO_START_PAY = 7;
    /**
     * 收款结束
     */
    public static final int OVER_PAY = 8;
    /**
     * 订单取消
     */
    public static final int ORDER_CANCEL = 9;


}
