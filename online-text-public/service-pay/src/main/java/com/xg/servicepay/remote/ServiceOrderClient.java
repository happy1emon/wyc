package com.xg.servicepay.remote;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @USER: XGGG
 * @DATE: 2023/4/13
 */
@FeignClient("service-order")
public interface ServiceOrderClient {

    @PostMapping("/order/pay")
    ResponseResult pay(@RequestBody OrderRequest orderRequest);

}
