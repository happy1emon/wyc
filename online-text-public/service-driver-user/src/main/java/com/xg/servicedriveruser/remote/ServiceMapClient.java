package com.xg.servicedriveruser.remote;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TerminalResponse;
import com.xg.internalcommon.response.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.POST,value = "/terminal/add")
    ResponseResult<TerminalResponse> addTerminal(@RequestParam String name,
                                                 @RequestParam Long desc);

    @RequestMapping(method = RequestMethod.POST,value = "/track/add")
    ResponseResult<TrackResponse> addTrack(@RequestParam String tid);


}
