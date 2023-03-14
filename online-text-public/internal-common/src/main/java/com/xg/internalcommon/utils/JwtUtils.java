package com.xg.internalcommon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xg.internalcommon.dto.TokenResult;

import java.util.*;

/**
 * 生成token和解析token的功能
 */
public class JwtUtils {
    //盐
    private static final String SIGN="XGLOVEXIAODAN!!@";
    private static final String JWT_KEY_PHONE="passengerPhone";
    //假设乘客是1 司机是2
    private static final String JWT_KEY_IDENTITY="identity";
    //token类型
    private static final String JWT_TOKEN_TYPE="tokenType";
    //生成token
    public static String generatorToken(String passengerPhone,String identity,String tokenType){
        //定义token过期时间
        Map<String,String> map=new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        //防止乘客和司机为同一个手机号 需要添加字段
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date=calendar.getTime();
        JWTCreator.Builder builder= JWT.create();
        //把map的值迭代放入builder中
        map.forEach(builder::withClaim);
        //整合过期时间 在服务端设置了有效期了 这里就不设置了
        //builder.withExpiresAt(date);
        //生成token
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    //解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity=verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult tokenResult=new TokenResult();
        tokenResult.setIdentity(identity);
        tokenResult.setPhone(phone);
        return tokenResult;
    }

    /**
     * 校验token  主要判断token是否异常
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        //定义返回结果 和返回信息
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) {
        }
        return null;
    }


    public static void main(String[] args) {
        String s = generatorToken("15666015992","1","accessToken");
        System.out.println("生成的token:\n"+s);
        TokenResult tokenResult = parseToken(s);
        System.out.println("解析的 token: \n"+tokenResult);
    }

}
