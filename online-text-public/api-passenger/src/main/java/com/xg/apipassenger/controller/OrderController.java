package com.xg.apipassenger.controller;

import com.xg.apipassenger.service.OrderService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/4/5
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){

        return orderService.add(orderRequest);
    }

}
