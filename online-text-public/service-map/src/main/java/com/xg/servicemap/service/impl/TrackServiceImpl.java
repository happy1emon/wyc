package com.xg.servicemap.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TrackResponse;
import com.xg.servicemap.remote.TrackClient;
import com.xg.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackClient trackClient;

    @Override
    public ResponseResult<TrackResponse> add(String tid) {
        return trackClient.add(tid);
    }
}
