package com.xg.serviceprice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.internalcommon.dto.PriceRule;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.serviceprice.mapper.PriceRuleMapper;
import com.xg.serviceprice.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @USER: XGGG
 * @DATE: 2023/4/6
 */
@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {

    @Autowired
    private PriceRuleService priceRuleService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody PriceRule priceRule) {
        return priceRuleService.add(priceRule);
    }

}
