package com.xg.internalcommon.dto;

import lombok.Data;

@Data
public class DictDistrict {

    private String addressCode;

    private String addressName;

    private String parentAddressCode;

    private Integer level;


}
