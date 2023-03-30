package com.xg.apidriver.service.impl;

import com.xg.apidriver.remote.ServiceDriverUserClient;
import com.xg.apidriver.remote.ServiceVerificationCodeClient;
import com.xg.apidriver.service.VerificationCodeService;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.DriverCarConstants;
import com.xg.internalcommon.constant.IdentityConstant;
import com.xg.internalcommon.constant.TokenConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.VerificationCodeDTO;
import com.xg.internalcommon.response.DriverUserExistsResponse;
import com.xg.internalcommon.response.NumberCodeResponse;
import com.xg.internalcommon.response.TokenResponse;
import com.xg.internalcommon.utils.JwtUtils;
import com.xg.internalcommon.utils.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult checkAndSendVerificationCode(String driverPhone) {
        //1 查询手机号是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriverPhone(driverPhone);
        Integer ifExists = driverUserExistsResponseResponseResult.getData().getIfExists();
        if (ifExists == DriverCarConstants.DRIVER_NOT_EXISTS){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(),CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        //2 获取验证码
        String queryDriverPhone=driverUserExistsResponseResponseResult.getData().getDriverPhone();
        ResponseResult<NumberCodeResponse> verificationCode = serviceVerificationCodeClient.getVerificationCode(6);
        NumberCodeResponse data = verificationCode.getData();
        int numberCode = data.getNumberCode();
//        int numberCode = data.getNumberCode();
        log.info("验证码为： "+numberCode);
        //3 调用第三方发送验证码
        //4 存入redis
        String key = RedisPrefixUtils.generatorKeyByPhone(queryDriverPhone, IdentityConstant.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);
        return ResponseResult.success("");
    }

    @Override
    public ResponseResult checkVerificationCode(String driverPhone, String verificationCode, String driverIdentity) {
        //生成Key
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, driverIdentity);
        //获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        //校验
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        // 颁发令牌
        //要定义枚举类型来定义司机和乘客 不应该用魔法值
        String accessToken = JwtUtils.generatorToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        //将accesstoken存到redis中
        String accessTokenkey = RedisPrefixUtils.generatorTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenkey, accessToken, 30, TimeUnit.DAYS);
        //将refreshToken存到redis中  且要比accessToken晚失效
        String refreshTokenkey = RedisPrefixUtils.generatorTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenkey, refreshToken, 31, TimeUnit.DAYS);
        //响应
        TokenResponse toeknResponse = new TokenResponse();
        toeknResponse.setAccessToken(accessToken);
        toeknResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(toeknResponse);
    }
}
