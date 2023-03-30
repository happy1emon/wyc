package com.xg.apiboss.service;

import com.xg.internalcommon.dto.DriverCarBindingRelationship;
import com.xg.internalcommon.dto.ResponseResult;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
public interface DriverCarRelationshipService {

    ResponseResult driverCarBind(DriverCarBindingRelationship driverCarBindingRelationship);

    ResponseResult driverCarUnbind(DriverCarBindingRelationship driverCarBindingRelationship);
}
