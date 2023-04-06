package com.xg.serviceprice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.internalcommon.dto.PriceRule;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.serviceprice.mapper.PriceRuleMapper;
import com.xg.serviceprice.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @USER: XGGG
 * @DATE: 2023/4/6
 */

@Service
public class PriceRuleServiceImpl implements PriceRuleService {

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    @Override
    public ResponseResult add(PriceRule priceRule) {
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + vehicleType;
        priceRule.setFareType(fareType);
        //问题1 增加版本号 前面两个字段无法确定唯一一条
        //问题2 找最大的版本号 需要排序
//        HashMap<String, Object> map=new HashMap<>();
//        map.put("cityCode",cityCode);
//        map.put("vehicleType",vehicleType);

        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if (priceRules.size() > 0) {
//            priceRuleMapper.insert(priceRule);
            fareVersion = priceRules.get(0).getFareVersion();
        }
        priceRule.setFareVersion(++fareVersion);
        int insert = priceRuleMapper.insert(priceRule);
        return ResponseResult.success(insert + "条数据已添加");
    }
}
