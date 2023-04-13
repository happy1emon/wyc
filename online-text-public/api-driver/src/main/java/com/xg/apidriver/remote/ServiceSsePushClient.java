package com.xg.apidriver.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @USER: XGGG
 * @DATE: 2023/4/13
 */
@FeignClient("service-sse-push")
public interface ServiceSsePushClient {

    @GetMapping("/push")
    String push(@RequestParam Long userId,@RequestParam String identity,@RequestParam String content );


}
