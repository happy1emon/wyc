package com.xg.internalcommon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @USER: XGGG
 * @DATE: 2023/4/5
 */
@Data
public class OrderRequest {

    /**
     * 乘客id
     */
    private Long passengerId;
    /**
     * 乘客电话
     */
    private String passengerPhone;

    /**
     * 下单行政区
     */
    private String address;
    /**
     * 出发时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime;
    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    /**
     * 出发地名称
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
     * 目的地名称
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
     * 坐标加密表示 1：GCJ-02 2:WGS84 3:BD-09 4:CGCS2000北斗 0:其他
     */
    private Integer encrypt;
    /**
     * 计价类型
     */
    private String fareType;

    /**
     * 计价类型版本
     */
    private Integer fareVersion;


    //加入header携带的设备号
    private String deviceCode;

}
