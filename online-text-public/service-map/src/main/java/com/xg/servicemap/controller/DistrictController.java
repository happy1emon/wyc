package com.xg.servicemap.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicemap.service.DictDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {


    @Autowired
    private DictDistrictService dictDistrictService;

    @GetMapping("/dic-district")
    public ResponseResult initDistrict(String keywords){
        ResponseResult responseResult = dictDistrictService.initDictDistrict(keywords);
        return ResponseResult.success();
    }
}
