package com.xg.serviceprice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.internalcommon.constant.CommonStatusEnum;
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
        String fareType = cityCode + "$" + vehicleType;
        priceRule.setFareType(fareType);
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if (priceRules.size() > 0) {
//            priceRuleMapper.insert(priceRule);
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EXISTS.getCode(), CommonStatusEnum.PRICE_RULE_EXISTS.getValue());

        }
        priceRule.setFareVersion(++fareVersion);
        int insert = priceRuleMapper.insert(priceRule);
        return ResponseResult.success(insert + "条数据已添加");
    }

    @Override
    public ResponseResult edit(PriceRule priceRule) {
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + "$" + vehicleType;
        priceRule.setFareType(fareType);
        //问题1 增加版本号 前面两个字段无法确定唯一一条
        //问题2 找最大的版本号 需要排序
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if (priceRules.size() > 0) {
            PriceRule latestPriceRule = priceRules.get(0);
            Double unitPricePerMile = latestPriceRule.getUnitPricePerMile();
            Double unitPricePerMinute = latestPriceRule.getUnitPricePerMinute();
            Double startFare = latestPriceRule.getStartFare();
            Integer startMile = latestPriceRule.getStartMile();
            if (unitPricePerMile.doubleValue() == priceRule.getUnitPricePerMile().doubleValue()
                    && unitPricePerMinute.doubleValue() == priceRule.getUnitPricePerMinute().doubleValue()
                    && startFare.doubleValue() == priceRule.getStartFare().doubleValue()
                    && startMile.intValue() == priceRule.getStartMile().intValue()) {
                return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_NOT_EDIT.getCode(), CommonStatusEnum.PRICE_RULE_NOT_EDIT.getValue());

            }
            fareVersion = latestPriceRule.getFareVersion();
        }
        priceRule.setFareVersion(++fareVersion);
        int insert = priceRuleMapper.insert(priceRule);
        return ResponseResult.success(insert + "条数据已添加");
    }

    @Override
    public ResponseResult<PriceRule> getNewestVersion(String fareType) {
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fare_type", fareType);
        queryWrapper.orderByDesc("fare_version");
        queryWrapper.select("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        return ResponseResult.success(priceRules.get(0));
    }

    @Override
    public ResponseResult<Boolean> isNew(String fareType, Integer fareVersion) {
        ResponseResult<PriceRule> newestVersion = getNewestVersion(fareType);
        PriceRule latestVersionRule = newestVersion.getData();
        Integer latestVersion = latestVersionRule.getFareVersion();
        return latestVersion == fareVersion ? ResponseResult.success(true) : ResponseResult.success(false);
    }

    @Override
    public ResponseResult<Boolean> ifExists(PriceRule priceRule) {
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        QueryWrapper<PriceRule> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type",vehicleType);
//        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.size()>0){
            return ResponseResult.success(true);
        }
        return ResponseResult.success(false);
    }
}
