package com.xg.serviceprice.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;
import com.xg.internalcommon.response.DirectionResponse;
import com.xg.internalcommon.response.ForecastPriceResponse;
import com.xg.serviceprice.remote.ServiceMapClient;
import com.xg.serviceprice.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Override
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("出发地的经度：" + depLongitude);
        log.info("出发地的纬度：" + depLatitude);
        log.info("目的地的经度：" + destLongitude);
        log.info("目的地的纬度：" + destLatitude);
        ForecastPriceDTO forecastPriceDTO=new ForecastPriceDTO();
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        log.info("调用地图服务，查询距离和时长");
        ResponseResult<DirectionResponse> distanceAndDuration = serviceMapClient.driving(forecastPriceDTO);
        String message = distanceAndDuration.getMessage();
        DirectionResponse data = distanceAndDuration.getData();
        log.info(data.toString());

        log.info("读取计价规则");


        log.info("根据距离、时长 与计价规则计算价格");

        ForecastPriceResponse forecastPriceResponse=new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);

        return ResponseResult.success(forecastPriceResponse);
    }
}
