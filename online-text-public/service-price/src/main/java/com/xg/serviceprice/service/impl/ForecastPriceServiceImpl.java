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
    public ResponseResult forecastPrice(String depLongitude,
                                        String depLatitude,
                                        String destLongitude,
                                        String destLatitude) {
        ForecastPriceDTO forecastPriceDTO=new ForecastPriceDTO();
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        log.info("调用地图服务，查询距离和时长");
        ResponseResult<DirectionResponse> distanceAndDuration = serviceMapClient.driving(forecastPriceDTO);
        DirectionResponse data = distanceAndDuration.getData();
        log.info(data.toString());

        log.info("读取计价规则");
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("city_code","110000");
        queryMap.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        //大于1也不对 只能找到一个规则
        if (priceRules.size()==0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        if(priceRules.size()>1){
            return ResponseResult.fail(CommonStatusEnum.PRICE_MULTIRULE_ERROR.getCode(),CommonStatusEnum.PRICE_MULTIRULE_ERROR.getValue());
        }
        log.info(priceRules.get(0).toString());
        log.info("根据距离、时长 与计价规则计算价格");
        PriceRule priceRule = priceRules.get(0);
        Double price = PriceCount.getPrice(data.getDistance(), data.getDuration(), priceRule);
        log.info(price.toString());
        ForecastPriceResponse forecastPriceResponse=new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }
}
