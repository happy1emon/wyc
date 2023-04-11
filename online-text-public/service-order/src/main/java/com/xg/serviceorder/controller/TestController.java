package com.xg.serviceorder.controller;

import com.xg.internalcommon.dto.OrderInfo;
import com.xg.serviceorder.mapper.OrderInfoMapper;
import com.xg.serviceorder.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @USER: XGGG
 * @DATE: 2023/4/5
 */
@RestController
public class TestController {
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    OrderInfoService orderInfoService;
    @RequestMapping("/test")
    public String test(){
        return "success";
    }
    @GetMapping("/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrDER(@PathVariable("orderId") long orderId){
        System.out.println("并发测试ID ："+orderId);
        OrderInfo orderInfo=orderInfoMapper.selectById(orderId);
        Boolean aBoolean = orderInfoService.dispatchRealTimeOrder(orderInfo);
        return "test-real-time-order success";

    }
}
