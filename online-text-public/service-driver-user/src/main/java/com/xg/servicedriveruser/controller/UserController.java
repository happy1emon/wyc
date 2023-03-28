package com.xg.servicedriveruser.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.DriverUser;
import com.xg.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/user")
    public ResponseResult userInsert(@RequestBody DriverUser driverUser){

        return driverUserService.addDriverUser(driverUser);
    }

}
