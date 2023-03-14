package com.xg.apipassenger.service.impl;

import com.xg.apipassenger.service.ForecastPriceService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;
import com.xg.internalcommon.response.ForecastPriceResponse;
import org.springframework.stereotype.Service;

@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {
    @Override
    public ResponseResult forecastPrice(String depLongtitude,
                                        String depLatitude,
                                        String destLongtitude,
                                        String destLatitude) {

        ForecastPriceResponse forecastPriceResponse=new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);
    }
}
