package com.xg.serviceprice.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;

public interface PriceService {

    ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO);

    /**
     * 计算实际价格
     * @param distance 距离
     * @param duration 时间
     * @param cityCode 城市编码
     * @param vehicleType 车辆类型
     * @return 啊
     */
    ResponseResult<Double> calculatePrice(Integer distance, Integer duration, String cityCode , String vehicleType);

}
