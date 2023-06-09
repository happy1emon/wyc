package com.xg.serviceprice.controller;


import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xg.internalcommon.dto.PriceRule;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.serviceprice.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule) {
        return priceRuleService.edit(priceRule);
    }
    @GetMapping("/get-newest-version")
    public ResponseResult getNewestVersion(@RequestParam String fareType){return priceRuleService.getNewestVersion(fareType);}
    @GetMapping("/is-new")
    public ResponseResult isNew(@RequestParam String fareType,@RequestParam Integer fareVersion){return priceRuleService.isNew(fareType,fareVersion);}
    @GetMapping("/if-exists")
    public ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule){return priceRuleService.ifExists(priceRule);  }

}
