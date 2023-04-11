package com.xg.servicedriveruser.controller;

import com.xg.internalcommon.constant.DriverCarConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.response.DriverUserExistsResponse;
import com.xg.internalcommon.response.OrderDriverResponse;
import com.xg.servicedriveruser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/user")
    public ResponseResult userInsert(@RequestBody DriverUser driverUser){

        return driverUserService.addDriverUser(driverUser);
    }

    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUser> getUser(@PathVariable("driverPhone") String driverPhone){
        ResponseResult<DriverUser> driver = driverUserService.queryDriverPhone(driverPhone);
        DriverUser driver1 = driver.getData();
        int ifExists= DriverCarConstants.DRIVER_EXISTS;
        if (driver1 == null){
            ifExists=DriverCarConstants.DRIVER_NOT_EXISTS;
        }
        DriverUserExistsResponse response=new DriverUserExistsResponse();
        response.setIfExists(ifExists);
        response.setDriverPhone(driverPhone);
        return ResponseResult.success(response);
    }

    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId){
        return driverUserService.getAvailableDriver(carId);
    }


}
