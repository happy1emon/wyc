package com.xg.servicemap.controller;

import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {


    @GetMapping("/dic-init")
    public ResponseResult initDistrict(){

        return null;
    }
}
