package com.xg.serviceprice.service;

import com.xg.internalcommon.dto.PriceRule;
import com.xg.internalcommon.dto.ResponseResult;

/**
 * @USER: XGGG
 * @DATE: 2023/4/6
 */
public interface PriceRuleService {

    ResponseResult add(PriceRule priceRule);

    ResponseResult edit(PriceRule priceRule);

    ResponseResult<PriceRule> getNewestVersion(String fareType);

    ResponseResult<Boolean> isNew(String fareType, Integer fareVersion);

    ResponseResult<Boolean> ifExists(PriceRule priceRule);

}
