package com.xg.serviceorder.remote;

import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @USER: XGGG
 * @DATE: 2023/4/10
 */

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @GetMapping("/city-driver/is-available-driver")
    ResponseResult<Boolean> isAvailableDriver(@RequestParam String cityCode);


}
