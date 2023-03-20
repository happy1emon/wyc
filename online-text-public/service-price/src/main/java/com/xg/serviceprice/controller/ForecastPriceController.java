package com.xg.serviceprice.controller;


import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;
import com.xg.serviceprice.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastPriceController {

    @Autowired
    private ForecastPriceService forecastPriceService;



    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {

        return forecastPriceService.forecastPrice(forecastPriceDTO.getDepLongitude(),
                forecastPriceDTO.getDepLatitude(),
                forecastPriceDTO.getDestLongitude(),
                forecastPriceDTO.getDestLatitude());
    }
}
