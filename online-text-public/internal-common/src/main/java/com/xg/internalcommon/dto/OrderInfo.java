package com.xg.internalcommon.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName order_info
 */
@TableName(value ="order_info")
@Data
public class OrderInfo implements Serializable {
    /**
     * 
     */

    private Long id;

    /**
     * 乘客id
     */
    private Long passengerId;

    /**
     * 乘客手机号
     */
    private String passengerPhone;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 司机手机号
     */
    private String driverPhone;

    /**
     * 车辆id
     */
    private Long carId;

    /**
     * 行政区域
     */
    private String address;

    /**
     * 下单时间
     */
    private LocalDateTime orderTime;

    /**
     * 出发时间
     */
    private LocalDateTime departTime;

    /**
     * 出发地
     */
    private String departure;

    /**
     * 出发地经度
     */
    private String depLongitude;

    /**
     * 出发地纬度
     */
    private String depLatitude;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 目的地经度
     */
    private String destLongitude;

    /**
     * 目的地纬度
     */
    private String destLatitude;

    /**
     * 坐标加密标识
     */
    private Integer encrypt;

    /**
     * 订价类型
     */
    private String fareType;

    /**
     * 接受时经度
     */
    private String receiveOrderCarLongitude;

    /**
     * 接单时间
     */
    private LocalDateTime receiveOrderTime;

    /**
     * 驾照
     */
    private String licenseId;

    /**
     * 车牌号
     */
    private String vehicleNo;

    /**
     * 预计接客时间
     */
    private LocalDateTime toPickUpPassengerTime;

    /**
     * 预计接客经度
     */
    private String toPickUpPassengerLongitude;

    /**
     * 预计接客纬度
     */
    private String toPickUpPassengerLatitude;

    /**
     * 接客地点
     */
    private String toPickUpPassengerAddress;

    /**
     * 司机到达出发地时间
     */
    private LocalDateTime driverArrivedDepartureTime;

    /**
     * 司机接到客人时间
     */
    private LocalDateTime pickUpPassengerTime;

    /**
     * 司机接到客人经度
     */
    private String pickUpPassengerLongitude;

    /**
     * 司机街道客人纬度
     */
    private String pickUpPassengerLatitude;

    /**
     * 乘客下车时间
     */
    private LocalDateTime passengerGetoffTime;

    /**
     * 乘客下车经度
     */
    private String passengerGetoffLongitude;

    /**
     * 乘客下车纬度
     */
    private String passengerGetoffLatitude;

    /**
     * 取消订单时间
     */
    private LocalDateTime cancelTime;

    /**
     * 撤销发起者1:乘客 2:驾驶员 3:平台 
     */
    private Integer cancelOperator;

    /**
     * 撤销类型代码:1乘客提前取消 2:司机提前撤销 3:平台公司撤销 4:乘客违约撤销 5:司机违约撤销
     */
    private Integer cancelTypeCode;

    /**
     * 行程 米
     */
    private Long driveMile;

    /**
     * 乘车时间 分钟
     */
    private Long driveTime;

    /**
     * 订单状态
     * 1:订单开始
     * 2:司机接单
     * 3:去接乘客
     * 4:司机到达乘客起点
     * 5:乘客上车，司机开始行程
     * 6: 到达目的地,行程结束,未支付
     * 7: 发起收款
     * 8:支付完成
     * 9:订单取消
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更改时间
     */
    private LocalDateTime gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}