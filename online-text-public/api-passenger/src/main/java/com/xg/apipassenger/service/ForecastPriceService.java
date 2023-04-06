package com.xg.apipassenger.service;

import com.xg.internalcommon.dto.ResponseResult;

public interface ForecastPriceService {
    /**
     * 根据 出发地和目的地的经纬度 预估价格
     * @param depLongtitude 出发地经度
     * @param depLatitude   出发地纬度
     * @param destLongtitude 目的地经度
     * @param destLatitude  目的地纬度
     * @return 响应结果
     */
    ResponseResult forecastPrice(String depLongtitude,
                                 String depLatitude,
                                 String destLongtitude,
                                 String destLatitude,
                                 String cityCode,
                                 String vehicleType);
}
