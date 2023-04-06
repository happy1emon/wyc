package com.xg.internalcommon.request;

import lombok.Data;

@Data
public class ForecastPriceDTO {

    private String cityCode;

    private String vehicleType;

    private Integer fareVersion;

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String destLatitude;

}
