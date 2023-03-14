package com.xg.apipassenger.controller;

import com.xg.apipassenger.service.ForecastPriceService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ForecastPriceController {

    @Autowired
    private ForecastPriceService forecastPriceService;

    @PostMapping("/forecast-price")
    public ResponseResult forcastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {
        log.info("出发地的经度：" + forecastPriceDTO.getDepLongtitude());
        log.info("出发地的纬度：" + forecastPriceDTO.getDepLatitude());
        log.info("目的地的经度：" + forecastPriceDTO.getDestLongtitude());
        log.info("目的地的纬度：" + forecastPriceDTO.getDestLatitude());

        log.info("调用计价服务，计算价格");

        String depLongtitude = forecastPriceDTO.getDepLongtitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongtitude = forecastPriceDTO.getDestLongtitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();

        return forecastPriceService.forecastPrice(depLongtitude,
                depLatitude, destLongtitude, destLatitude);
    }

}
