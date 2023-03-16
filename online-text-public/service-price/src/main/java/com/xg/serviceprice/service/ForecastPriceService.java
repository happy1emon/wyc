package com.xg.serviceprice.service;

import com.xg.internalcommon.dto.ResponseResult;

public interface ForecastPriceService {

    ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude);
}
