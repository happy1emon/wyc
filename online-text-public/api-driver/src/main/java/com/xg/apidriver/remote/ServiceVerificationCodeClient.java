package com.xg.apidriver.remote;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {
    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getVerificationCode(@PathVariable("size") int size);
}
