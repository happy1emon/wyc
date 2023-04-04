package com.xg.servicemap.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.PointRequest;
import com.xg.servicemap.remote.PointClient;
import com.xg.servicemap.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/4/4
 */
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointClient pointClient;

    @Override
    public ResponseResult upload(PointRequest pointRequest) {
        return pointClient.upload(pointRequest);
    }
}
