package com.xg.serviceprice.controller;


import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;
import com.xg.serviceprice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;


    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {

        return priceService.forecastPrice(forecastPriceDTO);
    }


    /**
     * 计算价格
     * @param distance 距离:米(m)
     * @param duration 时长：分钟(min)
     * @param cityCode 城市编码
     * @param vehicleType 车辆类型
     * @return
     */
    @PostMapping("/calculate-price")
    public ResponseResult calculatePrice(@RequestParam Integer distance, @RequestParam Integer duration,
                                         @RequestParam String cityCode, @RequestParam String vehicleType) {
        System.out.println(distance+duration+cityCode+vehicleType);
        //入参是分钟 但是计算的时候是按秒来的
        duration*=60;
        return priceService.calculatePrice(distance, duration, cityCode, vehicleType);
    }

}
