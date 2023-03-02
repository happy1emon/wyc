package com.xh.apipassenger.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.NumberCodeResponse;
import com.xh.apipassenger.remote.ServiceVerificationcodeClient;
import com.xh.apipassenger.service.VerificationCodeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;
    //乘客验证码前缀
    private String verificationCodePrefix="passenger-verification-code-";

    //kv值为String时用这个就可以
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult generatorCode(String passengerPhone) {
        //调用验证码服务 获得验证码
        System.out.println("调用服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode= numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code:"+numberCode);
        //存入Redis
        System.out.println("SAVE IN REDIS");
        //key value 过期时间
        String key=verificationCodePrefix + passengerPhone;
        //save in redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);
        //调用短信服务 阿里短信服务 腾讯短信通 华信 容联
        //RETURN
//        JSONObject result=new JSONObject();
//        result.put("code",1);
//        result.put("message","success");
        return ResponseResult.success();
    }
}
