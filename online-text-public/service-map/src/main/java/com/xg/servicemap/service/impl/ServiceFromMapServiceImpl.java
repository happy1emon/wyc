package com.xg.servicemap.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicemap.remote.ServiceClient;
import com.xg.servicemap.service.ServiceFromMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@Service
@Slf4j
public class ServiceFromMapServiceImpl implements ServiceFromMapService {

    @Autowired
    private ServiceClient serviceClient;

    @Override
    public ResponseResult add(String name) {
        return serviceClient.add(name);
    }
}
