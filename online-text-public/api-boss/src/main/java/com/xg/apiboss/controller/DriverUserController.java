package com.xg.apiboss.controller;

import com.xg.apiboss.service.DriverUserService;
import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/3/28
 */
@RestController
@Slf4j
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        driverUserService.addDriverUser(driverUser);
        return ResponseResult.success();
    }
    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriverUser(driverUser);
    }


}
