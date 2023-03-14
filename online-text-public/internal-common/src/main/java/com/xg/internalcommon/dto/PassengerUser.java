package com.xg.internalcommon.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName passenger_user
 */
@Data
@TableName(value ="passenger_user")
public class PassengerUser implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建表时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新表时间
     */
    private LocalDateTime gmtModified;

    /**
     * 乘客手机号
     */
    private String passengerPhone;

    /**
     * 乘客称谓
     */
    private String passengerName;

    /**
     * 乘客性别
     */
    private Integer passengerGender;

    /**
     * 乘客状态
     */
    private Integer state;

    private String profilePhoto;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 创建表时间
     */
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 创建表时间
     */
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 更新表时间
     */
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    /**
     * 更新表时间
     */
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 乘客手机号
     */
    public String getPassengerPhone() {
        return passengerPhone;
    }

    /**
     * 乘客手机号
     */
    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    /**
     * 乘客称谓
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * 乘客称谓
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * 乘客性别
     */
    public Integer getPassengerGender() {
        return passengerGender;
    }

    /**
     * 乘客性别
     */
    public void setPassengerGender(Integer passengerGender) {
        this.passengerGender = passengerGender;
    }

    /**
     * 乘客状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 乘客状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PassengerUser other = (PassengerUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getPassengerPhone() == null ? other.getPassengerPhone() == null : this.getPassengerPhone().equals(other.getPassengerPhone()))
            && (this.getPassengerName() == null ? other.getPassengerName() == null : this.getPassengerName().equals(other.getPassengerName()))
            && (this.getPassengerGender() == null ? other.getPassengerGender() == null : this.getPassengerGender().equals(other.getPassengerGender()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getPassengerPhone() == null) ? 0 : getPassengerPhone().hashCode());
        result = prime * result + ((getPassengerName() == null) ? 0 : getPassengerName().hashCode());
        result = prime * result + ((getPassengerGender() == null) ? 0 : getPassengerGender().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", passengerPhone=").append(passengerPhone);
        sb.append(", passengerName=").append(passengerName);
        sb.append(", passengerGender=").append(passengerGender);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", profilePhoto=").append(profilePhoto);
        sb.append("]");
        return sb.toString();
    }
}