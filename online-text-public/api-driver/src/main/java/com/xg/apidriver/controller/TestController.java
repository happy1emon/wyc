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

    /**
     * 需要授权的接口
     * @return
     */
    @GetMapping("/auth")
    public String testAuth(){
        return "auth!!!!";
    }

    /**
     * 不需要授权的接口
     * @return
     */
    @GetMapping("/noauth")
    public String testNoauth(){
        return "noauth!!";
    }
}
