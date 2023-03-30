package com.xg.apiboss.service.impl;

import com.xg.apiboss.remote.ServiceDriverUserClient;
import com.xg.apiboss.service.DriverCarRelationshipService;
import com.xg.internalcommon.dto.DriverCarBindingRelationship;
import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
@Service
public class DriverCarRelationshipServiceImpl implements DriverCarRelationshipService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Override
    public ResponseResult driverCarBind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    @Override
    public ResponseResult driverCarUnbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }
}
