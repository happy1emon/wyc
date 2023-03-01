package com.xh.apipassenger.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.NumberCodeResponse;
import com.xh.apipassenger.remote.ServiceVerificationcodeClient;
import com.xh.apipassenger.service.VerificationCodeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    @Override
    public String generatorCode(String passengerPhone) {
        //调用验证码服务 获得验证码
        System.out.println("调用服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode();
        int numberCode= numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code:"+numberCode);
        //存入Redis
        System.out.println("SAVE IN REDIS");

        //RETURN
        JSONObject result=new JSONObject();
        result.put("code",1);
        result.put("message","success");

        return result.toString();
    }
}
