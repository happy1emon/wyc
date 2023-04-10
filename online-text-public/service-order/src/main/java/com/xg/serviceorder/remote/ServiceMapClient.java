package com.xg.serviceorder.remote;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * @USER: XGGG
 * @DATE: 2023/4/10
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    @PostMapping("/terminal/aroundSearch")
    ResponseResult<ArrayList<TerminalResponse>> aroundSearch(@RequestParam String center, @RequestParam  String radius);
}
