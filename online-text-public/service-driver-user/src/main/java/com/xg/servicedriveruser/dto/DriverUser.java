package com.xg.servicedriveruser.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName driver_user
 */
@TableName(value ="driver_user")
@Data
public class DriverUser implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 注册地行政区划代码
     */
    private String address;

    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 司机手机号
     */
    private String driverPhone;

    /**
     * 性别 0不详 1男2女
     */
    private Integer driverGender;

    /**
     * 出生日期
     */
    private Date driverBirthday;

    /**
     * 民族
     */
    private String driverNation;

    /**
     * 联系地址
     */
    private String driverContactAddress;

    /**
     * 驾驶证号
     */
    private String licenseId;

    /**
     * 初次领取驾证日期
     */
    private Date getDriverLisenceDate;

    /**
     * 驾驶证有效期起始
     */
    private Date driverLicenseOn;

    /**
     * 驾驶证有效期截止
     */
    private Date driverLicenseOff;

    /**
     * 是否巡游出租车 1是0否
     */
    private Integer taxiDriver;

    /**
     * 网络预约出租汽车驾驶员资格证号
     */
    private String certificateNo;

    /**
     * 
     */
    private String networkCarIssueOrganization;

    /**
     * 发证日期
     */
    private Date networkCarIssueDate;

    /**
     * 初次领取资格证日期
     */
    private Date getNetworkCarProofDate;

    /**
     * 有效期
     */
    private Date networkCarProofOn;

    /**
     * 截止日期
     */
    private Date networkCarProofOff;

    /**
     * 报备日期
     */
    private Date registerDate;

    /**
     * 服务类型 1网络预约出租车2巡游出租汽车3私人小客车合乘
     */
    private Integer commercialType;

    /**
     * 驾驶员合同(协议)签署公司
     */
    private String contractCompany;

    /**
     * 合同期起
     */
    private Date contractOn;

    /**
     * 合同期止
     */
    private Date contractOff;

    /**
     * 状态 0：有效 1：失效
     */
    private Integer state;

    /**
     * 操作标识 1：新增 2：更新 3：删除
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}