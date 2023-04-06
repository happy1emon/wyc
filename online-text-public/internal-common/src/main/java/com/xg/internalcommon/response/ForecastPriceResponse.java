package com.xg.internalcommon.response;

import lombok.Data;

@Data
public class ForecastPriceResponse {
    private Double price;
    private String cityCode;
    private String vehicleType;
    private String fareType;
    private Integer fareVersion;
}
