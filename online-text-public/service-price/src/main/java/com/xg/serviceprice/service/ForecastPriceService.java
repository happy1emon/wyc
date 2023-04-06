package com.xg.serviceprice.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;

public interface ForecastPriceService {

    ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO);
}
