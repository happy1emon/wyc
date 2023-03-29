package com.xg.servicedriveruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.internalcommon.dto.DriverCarBindingRelationship;
import com.xg.internalcommon.dto.ResponseResult;

/**
* @author junxuan
* @description 针对表【driver_car_binding_relationship】的数据库操作Service
* @createDate 2023-03-29 17:08:54
*/
public interface DriverCarBindingRelationshipService extends IService<DriverCarBindingRelationship> {


    ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship);

    ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship);
}
