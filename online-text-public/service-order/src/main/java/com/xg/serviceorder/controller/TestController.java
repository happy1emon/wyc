package com.xg.serviceorder.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/4/5
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "success";
    }

}
