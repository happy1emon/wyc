package com.xg.servicemap.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TrackResponse;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
public interface TrackService {

    ResponseResult<TrackResponse> add(String tid);

}
