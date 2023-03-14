package com.xh.apipassenger.service.impl;

import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.TokenConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.TokenResult;
import com.xg.internalcommon.response.TokenResponse;
import com.xg.internalcommon.utils.JwtUtils;
import com.xg.internalcommon.utils.RedisPrefixUtils;
import com.xh.apipassenger.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImple implements TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult refreshToken(String refreshTokenSrc) {
        //解析refreshToken 判断是否合法
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
        //去读取redis中 的refreshtoken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenKeyRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        //校验refreshToken
        if (StringUtils.isBlank(refreshTokenKeyRedis)
                ||
                (!refreshTokenKeyRedis.trim().equals(refreshTokenKey.trim()))) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        //生成双token
        String refreshToken = JwtUtils.generatorToken(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);
        TokenResponse tokenResponse=new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);
        return ResponseResult.success(tokenResponse);
    }
}
