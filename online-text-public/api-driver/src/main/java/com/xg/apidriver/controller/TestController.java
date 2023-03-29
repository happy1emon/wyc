package com.xg.apidriver.controller;

import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/3/29
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public static ResponseResult test(){
        return ResponseResult.success();
    }
}
