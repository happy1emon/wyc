package com.xg.serviceprice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.dto.PriceRule;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ForecastPriceDTO;
import com.xg.internalcommon.response.DirectionResponse;
import com.xg.internalcommon.response.ForecastPriceResponse;
import com.xg.serviceprice.mapper.PriceRuleMapper;
import com.xg.serviceprice.remote.ServiceMapClient;
import com.xg.serviceprice.service.ForecastPriceService;
import com.xg.serviceprice.utiles.PriceCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;


    @Override
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
        ResponseResult<DirectionResponse> distanceAndDuration = serviceMapClient.driving(forecastPriceDTO);
        DirectionResponse data = distanceAndDuration.getData();
        String cityCode = forecastPriceDTO.getCityCode();
        String vehicleType = forecastPriceDTO.getVehicleType();
        log.info(data.toString());
        QueryWrapper<PriceRule> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type",vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        //大于1也不对 只能找到一个规则
        if (priceRules.size()==0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        log.info(priceRules.get(0).toString());
        PriceRule priceRule = priceRules.get(0);
        Double price = PriceCount.getPrice(data.getDistance(), data.getDuration(), priceRule);
        log.info(price.toString());
        ForecastPriceResponse forecastPriceResponse=new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        forecastPriceResponse.setCityCode(cityCode);
        forecastPriceResponse.setVehicleType(vehicleType);
        forecastPriceResponse.setFareVersion(priceRule.getFareVersion());
        forecastPriceResponse.setFareType(priceRule.getFareType());
        return ResponseResult.success(forecastPriceResponse);
    }
}

