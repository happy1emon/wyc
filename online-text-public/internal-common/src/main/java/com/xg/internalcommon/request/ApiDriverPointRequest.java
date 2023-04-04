package com.xg.internalcommon.request;

import com.xg.internalcommon.dto.PointDTO;
import lombok.Data;

/**
 * @USER: XGGG
 * @DATE: 2023/4/4
 */
@Data
public class ApiDriverPointRequest {
    private Long carId;
    private PointDTO[] points;
}
