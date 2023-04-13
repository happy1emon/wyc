package com.xg.serviceorder.remote;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xg.internalcommon.dto.PriceRule;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @USER: XGGG
 * @DATE: 2023/4/6
 */
@FeignClient("service-price")
public interface ServicePriceClient {
    @RequestMapping(method = RequestMethod.GET,value = "/price-rule/is-new")
    ResponseResult<Boolean> isNew(@RequestParam String fareType,@RequestParam Integer fareVersion);

    @RequestMapping(method = RequestMethod.GET , value = "/price-rule/if-exists")
    ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule);

    @RequestMapping(method = RequestMethod.POST,value = "/calculate-price")
    ResponseResult<Double> calculatePrice(@RequestParam Integer distance,@RequestParam Integer duration,@RequestParam String cityCode,@RequestParam String vehicleType);

}
