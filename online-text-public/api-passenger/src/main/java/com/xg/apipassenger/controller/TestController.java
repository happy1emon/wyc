package com.xg.apipassenger.controller;

import com.xg.apipassenger.service.OrderService;
import com.xg.internalcommon.dto.ResponseResult;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test apiPassenger";
    }

    /**
     * 需要有token
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }

    /**
     * 没有token也可以返回
     * @return
     */
    @GetMapping("/noauthTest")
    public ResponseResult noauthTest(){
        return ResponseResult.success("noauth test");
    }

    @Autowired
    OrderService orderService;

    @GetMapping("/test-clucher-order/{orderId}")
    public String testClucherOrder(@PathVariable("orderId") Long orderId){
        System.out.println(orderId);
        String s = orderService.testClucherOrder(orderId);
        return "test-clucher-order";

    }

}
