package com.xg.apidriver.remote;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.PointRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

/**
 * @USER: XGGG
 * @DATE: 2023/4/4
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.POST,value = "/point/upload")
    ResponseResult upload(@RequestBody PointRequest pointRequest);

}
