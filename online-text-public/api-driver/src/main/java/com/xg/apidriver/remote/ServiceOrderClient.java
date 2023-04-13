package com.xg.apidriver.remote;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import org.aspectj.weaver.ast.Or;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @USER: XGGG
 * @DATE: 2023/4/12
 */
@FeignClient("service-order")
public interface ServiceOrderClient {

    @PostMapping("/order/to-pick-up-passenger")
    ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest);

    @PostMapping("/order/arrived-departure")
    ResponseResult arrivedDeparture(@RequestBody OrderRequest orderRequest);

    @PostMapping("/order/pick-up-passenger")
    ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest);

    @PostMapping("/order/passenger-getoff")
    ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest);

    @PostMapping("/order/cancel")
    ResponseResult cancel(@RequestParam Long orderId,@RequestParam String identity);
}
