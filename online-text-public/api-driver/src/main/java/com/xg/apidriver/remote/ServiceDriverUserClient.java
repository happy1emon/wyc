package com.xg.apidriver.remote;

import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Driver;

/**
 * @USER: XGGG
 * @DATE: 2023/3/29
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    ResponseResult updateUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.GET,value = "/check-driver/{driverPhone}")
    ResponseResult<DriverUserExistsResponse> checkDriverPhone(@PathVariable("driverPhone") String driverPhone);
}
