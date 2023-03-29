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
 * @TableName driver_car_binding_relationship
 */
@TableName(value ="driver_car_binding_relationship")
@Data
public class DriverCarBindingRelationship implements Serializable {
    /**
     * 
     */
//    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long driverId;

    /**
     * 
     */
    private Long carId;

    /**
     * 绑定状态 1绑定 2解绑
     */
    private Integer bindState;

    /**
     * 
     */
    private LocalDateTime bindingTime;

    /**
     * 
     */
    private LocalDateTime unBindingTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}