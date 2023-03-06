package com.xh.apipassenger.service.impl;

import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.NumberCodeResponse;
import com.xg.internalcommon.response.ToeknResponse;
import com.xh.apipassenger.remote.ServiceVerificationcodeClient;
import com.xh.apipassenger.service.VerificationCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
/*
    1 调用子服务的api 获得验证码
    2 key value 过期时间
    3 save in redis
    4 调用短信服务 阿里短信服务 腾讯短信通 华信 容联
    5 RETURN
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    //OpenFeign远程调用接口
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;
    //乘客验证码前缀
    private final String verificationCodePrefix="passenger-verification-code-";
    //kv值为String时用这个就可以
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public ResponseResult generatorCode(String passengerPhone) {
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode= numberCodeResponse.getData().getNumberCode();
        String key=generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);
        return ResponseResult.success();
    }
    @Override
    public ResponseResult checkVerificationCode(String passengerPhone,String verificationCode) {
        //1、 根据手机号 在redis读取验证码
        System.out.println("1、 根据手机号 在redis读取验证码");
        //生成key
        String key=generatorKeyByPhone(passengerPhone);
        //根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        //2、 校验验证码
        if (StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        //3、 判断原来是否有用户，并进行对应的处理
        System.out.println("checking passenger");


        // 颁发令牌
        System.out.println("assign token");

        //响应
        ToeknResponse toeknResponse=new ToeknResponse();
        toeknResponse.setToken("token value");
        return ResponseResult.success(toeknResponse);
    }
    private String generatorKeyByPhone(String passengerPhone){
        return verificationCodePrefix+passengerPhone;
    }



}
