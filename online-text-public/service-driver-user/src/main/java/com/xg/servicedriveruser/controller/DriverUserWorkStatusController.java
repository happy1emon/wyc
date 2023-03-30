package com.xg.servicedriveruser.controller;

import com.xg.internalcommon.dto.DriverUserWorkStatus;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicedriveruser.service.DriverUserWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
@RestController
public class DriverUserWorkStatusController {
    @Autowired
    private DriverUserWorkStatusService driverUserWorkStatusService;

    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWokeStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        Integer workStatus = driverUserWorkStatus.getWorkStatus();
        Long driverId = driverUserWorkStatus.getDriverId();

        return driverUserWorkStatusService.changeWorkStatus(driverId,workStatus);
    }
}
