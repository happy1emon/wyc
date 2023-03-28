package generator;

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
    private Integer flage;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        DriverUser other = (DriverUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getDriverName() == null ? other.getDriverName() == null : this.getDriverName().equals(other.getDriverName()))
            && (this.getDriverPhone() == null ? other.getDriverPhone() == null : this.getDriverPhone().equals(other.getDriverPhone()))
            && (this.getDriverGender() == null ? other.getDriverGender() == null : this.getDriverGender().equals(other.getDriverGender()))
            && (this.getDriverBirthday() == null ? other.getDriverBirthday() == null : this.getDriverBirthday().equals(other.getDriverBirthday()))
            && (this.getDriverNation() == null ? other.getDriverNation() == null : this.getDriverNation().equals(other.getDriverNation()))
            && (this.getDriverContactAddress() == null ? other.getDriverContactAddress() == null : this.getDriverContactAddress().equals(other.getDriverContactAddress()))
            && (this.getLicenseId() == null ? other.getLicenseId() == null : this.getLicenseId().equals(other.getLicenseId()))
            && (this.getGetDriverLisenceDate() == null ? other.getGetDriverLisenceDate() == null : this.getGetDriverLisenceDate().equals(other.getGetDriverLisenceDate()))
            && (this.getDriverLicenseOn() == null ? other.getDriverLicenseOn() == null : this.getDriverLicenseOn().equals(other.getDriverLicenseOn()))
            && (this.getDriverLicenseOff() == null ? other.getDriverLicenseOff() == null : this.getDriverLicenseOff().equals(other.getDriverLicenseOff()))
            && (this.getTaxiDriver() == null ? other.getTaxiDriver() == null : this.getTaxiDriver().equals(other.getTaxiDriver()))
            && (this.getCertificateNo() == null ? other.getCertificateNo() == null : this.getCertificateNo().equals(other.getCertificateNo()))
            && (this.getNetworkCarIssueOrganization() == null ? other.getNetworkCarIssueOrganization() == null : this.getNetworkCarIssueOrganization().equals(other.getNetworkCarIssueOrganization()))
            && (this.getNetworkCarIssueDate() == null ? other.getNetworkCarIssueDate() == null : this.getNetworkCarIssueDate().equals(other.getNetworkCarIssueDate()))
            && (this.getGetNetworkCarProofDate() == null ? other.getGetNetworkCarProofDate() == null : this.getGetNetworkCarProofDate().equals(other.getGetNetworkCarProofDate()))
            && (this.getNetworkCarProofOn() == null ? other.getNetworkCarProofOn() == null : this.getNetworkCarProofOn().equals(other.getNetworkCarProofOn()))
            && (this.getNetworkCarProofOff() == null ? other.getNetworkCarProofOff() == null : this.getNetworkCarProofOff().equals(other.getNetworkCarProofOff()))
            && (this.getRegisterDate() == null ? other.getRegisterDate() == null : this.getRegisterDate().equals(other.getRegisterDate()))
            && (this.getCommercialType() == null ? other.getCommercialType() == null : this.getCommercialType().equals(other.getCommercialType()))
            && (this.getContractCompany() == null ? other.getContractCompany() == null : this.getContractCompany().equals(other.getContractCompany()))
            && (this.getContractOn() == null ? other.getContractOn() == null : this.getContractOn().equals(other.getContractOn()))
            && (this.getContractOff() == null ? other.getContractOff() == null : this.getContractOff().equals(other.getContractOff()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getFlage() == null ? other.getFlage() == null : this.getFlage().equals(other.getFlage()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getDriverName() == null) ? 0 : getDriverName().hashCode());
        result = prime * result + ((getDriverPhone() == null) ? 0 : getDriverPhone().hashCode());
        result = prime * result + ((getDriverGender() == null) ? 0 : getDriverGender().hashCode());
        result = prime * result + ((getDriverBirthday() == null) ? 0 : getDriverBirthday().hashCode());
        result = prime * result + ((getDriverNation() == null) ? 0 : getDriverNation().hashCode());
        result = prime * result + ((getDriverContactAddress() == null) ? 0 : getDriverContactAddress().hashCode());
        result = prime * result + ((getLicenseId() == null) ? 0 : getLicenseId().hashCode());
        result = prime * result + ((getGetDriverLisenceDate() == null) ? 0 : getGetDriverLisenceDate().hashCode());
        result = prime * result + ((getDriverLicenseOn() == null) ? 0 : getDriverLicenseOn().hashCode());
        result = prime * result + ((getDriverLicenseOff() == null) ? 0 : getDriverLicenseOff().hashCode());
        result = prime * result + ((getTaxiDriver() == null) ? 0 : getTaxiDriver().hashCode());
        result = prime * result + ((getCertificateNo() == null) ? 0 : getCertificateNo().hashCode());
        result = prime * result + ((getNetworkCarIssueOrganization() == null) ? 0 : getNetworkCarIssueOrganization().hashCode());
        result = prime * result + ((getNetworkCarIssueDate() == null) ? 0 : getNetworkCarIssueDate().hashCode());
        result = prime * result + ((getGetNetworkCarProofDate() == null) ? 0 : getGetNetworkCarProofDate().hashCode());
        result = prime * result + ((getNetworkCarProofOn() == null) ? 0 : getNetworkCarProofOn().hashCode());
        result = prime * result + ((getNetworkCarProofOff() == null) ? 0 : getNetworkCarProofOff().hashCode());
        result = prime * result + ((getRegisterDate() == null) ? 0 : getRegisterDate().hashCode());
        result = prime * result + ((getCommercialType() == null) ? 0 : getCommercialType().hashCode());
        result = prime * result + ((getContractCompany() == null) ? 0 : getContractCompany().hashCode());
        result = prime * result + ((getContractOn() == null) ? 0 : getContractOn().hashCode());
        result = prime * result + ((getContractOff() == null) ? 0 : getContractOff().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getFlage() == null) ? 0 : getFlage().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", address=").append(address);
        sb.append(", driverName=").append(driverName);
        sb.append(", driverPhone=").append(driverPhone);
        sb.append(", driverGender=").append(driverGender);
        sb.append(", driverBirthday=").append(driverBirthday);
        sb.append(", driverNation=").append(driverNation);
        sb.append(", driverContactAddress=").append(driverContactAddress);
        sb.append(", licenseId=").append(licenseId);
        sb.append(", getDriverLisenceDate=").append(getDriverLisenceDate);
        sb.append(", driverLicenseOn=").append(driverLicenseOn);
        sb.append(", driverLicenseOff=").append(driverLicenseOff);
        sb.append(", taxiDriver=").append(taxiDriver);
        sb.append(", certificateNo=").append(certificateNo);
        sb.append(", networkCarIssueOrganization=").append(networkCarIssueOrganization);
        sb.append(", networkCarIssueDate=").append(networkCarIssueDate);
        sb.append(", getNetworkCarProofDate=").append(getNetworkCarProofDate);
        sb.append(", networkCarProofOn=").append(networkCarProofOn);
        sb.append(", networkCarProofOff=").append(networkCarProofOff);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", commercialType=").append(commercialType);
        sb.append(", contractCompany=").append(contractCompany);
        sb.append(", contractOn=").append(contractOn);
        sb.append(", contractOff=").append(contractOff);
        sb.append(", state=").append(state);
        sb.append(", flage=").append(flage);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}