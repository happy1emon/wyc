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
 * @TableName driver_user_work_status
 */
@TableName(value ="driver_user_work_status")
@Data
public class DriverUserWorkStatus implements Serializable {

    private Long id;

    /**
     * 
     */
    private Long driverId;

    /**
     * 
     */
    private Integer workStatus;

    /**
     * 
     */
    private LocalDateTime gmtCreate;

    /**
     * 
     */
    private LocalDateTime gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}