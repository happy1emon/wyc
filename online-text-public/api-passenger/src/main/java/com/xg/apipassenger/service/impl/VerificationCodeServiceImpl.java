package com.xg.apipassenger.service.impl;

import com.xg.apipassenger.remote.ServicePassengerUserClient;
import com.xg.apipassenger.remote.ServiceVerificationcodeClient;
import com.xg.apipassenger.service.VerificationCodeService;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.IdentityConstant;
import com.xg.internalcommon.constant.TokenConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.VerificationCodeDTO;
import com.xg.internalcommon.response.NumberCodeResponse;
import com.xg.internalcommon.response.TokenResponse;
import com.xg.internalcommon.utils.JwtUtils;
import com.xg.internalcommon.utils.RedisPrefixUtils;
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
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    //kv值为String时用这个就可以
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult generatorCode(String passengerPhone) {
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult checkVerificationCode(String passengerPhone, String verificationCode) {
        //1、 根据手机号 在redis读取验证码
//        System.out.println("1、 根据手机号 在redis读取验证码");
        //生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        //根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        //2、 校验验证码
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        //3、 判断原来是否有用户，并进行对应的处理
        System.out.println("checking passenger");
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        //调用远程服务
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);
        // 颁发令牌
        //要定义枚举类型来定义司机和乘客 不应该用魔法值
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        //将accesstoken存到redis中
        String accessTokenkey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenkey, accessToken, 10, TimeUnit.SECONDS);
        //将refreshToken存到redis中  且要比accessToken晚失效
        String refreshTokenkey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenkey, refreshToken, 90, TimeUnit.SECONDS);
        //响应
        TokenResponse toeknResponse = new TokenResponse();
        toeknResponse.setAccessToken(accessToken);
        toeknResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(toeknResponse);
    }


}
