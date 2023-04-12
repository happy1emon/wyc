package com.xg.serviceorder.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @USER: XGGG
 * @DATE: 2023/4/12
 */
@FeignClient("service-sse-push")
public interface ServiceSsePushClient {

    @RequestMapping(method = RequestMethod.GET,value="/push")
    String push(@RequestParam Long userId,@RequestParam String identity,@RequestParam String content);

}
