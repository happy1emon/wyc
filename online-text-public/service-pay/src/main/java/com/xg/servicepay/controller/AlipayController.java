package com.xg.servicepay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @USER: XGGG
 * @DATE: 2023/4/13
 */
@Controller
@RequestMapping("/alipay")
@ResponseBody
public class AlipayController {

    @GetMapping("/pay")
    public String pay(String subject,String outTradeNo,String totalAmount){
        AlipayTradePagePayResponse response=null;
        try {
            //这里不能加http 加上会认为是用GET方法
            response = Factory.Payment.Page().pay(subject,outTradeNo,totalAmount,"asd2828282.w3.luyouxia.net/alipay/notify");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.getBody();
    }
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) throws Exception {
        String trade_status = request.getParameter("trade_status");
        if (trade_status.trim().equals("TRADE_SUCCESS")){
            Map<String,String> param=new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (String name:parameterMap.keySet()) {
                param.put(name,request.getParameter(name));
            }
            //验证
            if (Factory.Payment.Common().verifyNotify(param)){
                System.out.println("通过支付宝验证");
                for (String name:parameterMap.keySet()) {
                    System.out.println("收到并且接受的参数：");
                    System.out.println(name+","+param.get(name));
                }


            }else{
                System.out.println("支付宝验证不通过");
            }
        }

        return "success";
    }

}
