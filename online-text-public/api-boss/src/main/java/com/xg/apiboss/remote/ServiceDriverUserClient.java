package com.xg.apiboss.remote;

import com.xg.apiboss.service.DriverUserService;
import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @USER: XGGG
 * @DATE: 2023/3/28
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    ResponseResult addDriverUser(@RequestBody DriverUser driverUser);
}
