package com.xg.apidriver.controller;

import com.xg.apidriver.service.OrderInfoService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/4/12
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/to-pick-up-passenger")
    public ResponseResult changeStatus(@RequestBody OrderRequest orderRequest){
        return orderInfoService.toPickUpPassenger(orderRequest);
    }

    

}
