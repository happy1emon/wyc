package com.xg.apipassenger.remote;


import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;
import com.xg.internalcommon.response.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-price")
public interface ServicePriceClient {
    @RequestMapping(method = RequestMethod.POST,value = "/forecast-price")
    ResponseResult<ForecastPriceResponse> getPrice(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
