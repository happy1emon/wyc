package com.xg.testalipay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
@Controller
public class TestAlipayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAlipayApplication.class, args);
    }


    @PostMapping("/test")
    public String test(){
        System.out.println("支付宝回调了");
        return "sucess";
    }

}
