package com.xg.servicemap.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.PointRequest;

/**
 * @USER: XGGG
 * @DATE: 2023/4/4
 */

public interface PointService {

    ResponseResult upload(PointRequest pointRequest);

}
