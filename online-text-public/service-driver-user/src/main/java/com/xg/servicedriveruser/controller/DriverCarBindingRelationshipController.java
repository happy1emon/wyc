package com.xg.servicedriveruser.controller;

import com.xg.internalcommon.dto.DriverCarBindingRelationship;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicedriveruser.service.DriverCarBindingRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/3/29
 */
@RestController
public class DriverCarBindingRelationshipController {

    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;


    @PostMapping("/driver-car-binding-relationship/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){

        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }

    @PostMapping("/driver-car-binding-relationship/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){

        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }


}
