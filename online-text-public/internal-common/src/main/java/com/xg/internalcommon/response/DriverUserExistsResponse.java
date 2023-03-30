package com.xg.internalcommon.response;

import lombok.Data;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
@Data
public class DriverUserExistsResponse {
    private String driverPhone;
    private Integer ifExists;
}
