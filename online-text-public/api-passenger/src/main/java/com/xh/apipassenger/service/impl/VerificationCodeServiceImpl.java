package com.xh.apipassenger.service.impl;

import com.xh.apipassenger.service.VerificationCodeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Override
    public String generatorCode(String passengerPhone) {
        //调用验证码服务 获得验证码
        System.out.println("调用服务，获取验证码");
        String code="111";

        //存入Redis
        System.out.println("SAVE IN REDIS");

        //RETURN
        JSONObject result=new JSONObject();
        result.put("code",1);
        result.put("message","success");

        return result.toString();
    }
}
