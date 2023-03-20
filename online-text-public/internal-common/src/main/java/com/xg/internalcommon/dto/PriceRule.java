package com.xg.internalcommon.dto;

import lombok.Data;

@Data
public class PriceRule {
    private String cityCode;

    private String vehicleType;

    private Double StartFare;

    private Integer startMile;

    private Double unitPricePerMile;

    private Double unitPricePerMinute;
}
