package com.xg.apidriver.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ApiDriverPointRequest;

/**
 * @USER: XGGG
 * @DATE: 2023/4/4
 */
public interface PointService {
    ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest);
}
