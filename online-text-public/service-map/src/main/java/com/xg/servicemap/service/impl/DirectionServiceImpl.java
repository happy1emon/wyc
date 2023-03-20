package com.xg.servicemap.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.DirectionResponse;
import com.xg.servicemap.remote.MapDirectionClient;
import com.xg.servicemap.service.DirecationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionServiceImpl implements DirecationService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    @Override
    public ResponseResult driving(String depLongitude,
                                  String depLatitude,
                                  String destLongitude,
                                  String destLatitude) {
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);
        return ResponseResult.success(direction);
    }
}
