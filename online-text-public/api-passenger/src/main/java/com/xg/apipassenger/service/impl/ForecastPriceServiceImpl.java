package com.xg.apipassenger.service.impl;

import com.xg.apipassenger.remote.ServicePriceClient;
import com.xg.apipassenger.service.ForecastPriceService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;
import com.xg.internalcommon.response.ForecastPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;

    @Override
    public ResponseResult forecastPrice(String depLongtitude,
                                        String depLatitude,
                                        String destLongtitude,
                                        String destLatitude,
                                        String cityCode,
                                        String vehicleType) {

        ForecastPriceDTO forecastPriceDTO=new ForecastPriceDTO();
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setDestLongitude(destLongtitude);
        forecastPriceDTO.setDepLongitude(depLongtitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setCityCode(cityCode);
        forecastPriceDTO.setVehicleType(vehicleType);
        return servicePriceClient.getPrice(forecastPriceDTO);

    }
}
