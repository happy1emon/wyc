package com.xg.apidriver.controller;

import com.xg.apidriver.service.PayService;
import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/4/13
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;


    @PostMapping("/push-pay-info")
    public ResponseResult pushPayInfo(@RequestParam Long orderId,@RequestParam Double price,@RequestParam Long passengerId){
        return payService.pushPayInfo(orderId,price,passengerId);
    }

}
