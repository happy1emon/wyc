package com.xg.serviceverficationcode.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("size:"+size);
        //生成验证码
        // 获取随机数
        double mathRandom = (Math.random() * 9 + 1) * (Math.pow(10,size-1));
        int resultInt= (int) mathRandom;
        System.out.println("generate code:"+resultInt);
        // 小数点后移5位
        NumberCodeResponse numberCodeResponse=new NumberCodeResponse();
        numberCodeResponse.setNumberCode(resultInt);
        ResponseResult success = ResponseResult.success(numberCodeResponse);
        return success;
    }

    public static void main(String[] args) {


    }
}
