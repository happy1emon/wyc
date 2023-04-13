package com.xg.apipassenger.remote;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @USER: XGGG
 * @DATE: 2023/4/5
 */
@FeignClient("service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST,value = "/order/add")
    ResponseResult add(@RequestBody OrderRequest orderRequest);

    @GetMapping("/test-real-time-order/{orderId}")
    String testClucherOrder(@PathVariable("orderId") Long orderId);

    @PostMapping("/order/cancel")
    ResponseResult cancel(@RequestParam Long orderId,@RequestParam String identity);
}
