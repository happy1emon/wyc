package com.xg.internalcommon.response;

import lombok.Data;

/**
 * @USER: XGGG
 * @DATE: 2023/4/11
 */
@Data
public class OrderDriverResponse {
    private Long driverId;
    private Long carId;
    private String driverPhone;
    private String vehicleNo;
    private String licenseId;
    private String vehicleType;

}
