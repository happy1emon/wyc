package com.xg.serviceorder.remote;

import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * @USER: XGGG
 * @DATE: 2023/4/10
 */

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @GetMapping("/city-driver/is-available-driver")
    ResponseResult<Boolean> isAvailableDriver(@RequestParam String cityCode);

    @GetMapping("/get-available-driver/{carId}")
    ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId);

    @GetMapping("/queryCarByCarId")
    ResponseResult<Car> getCarById(@RequestParam Long carId);
}
