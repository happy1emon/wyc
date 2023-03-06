package com.xg.internalcommon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xg.internalcommon.dto.TokenResult;
import jdk.nashorn.internal.parser.Token;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成token和解析token的功能
 */
public class JwtUtils {
    //盐
    private static final String SIGN="XGLOVEXIAODAN!!@";
    private static final String JWT_KEY_PHONE="passengerPhone";
    //假设乘客是1 司机是2
    private static final String JWT_KEY_IDENTITY="identity";
    //生成token
    public static String generatorToken(String passengerPhone,String identity){
        //定义token过期时间
        Map<String,String> map=new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        //防止乘客和司机为同一个手机号 需要添加字段
        map.put(JWT_KEY_IDENTITY,identity);
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date=calendar.getTime();
        JWTCreator.Builder builder= JWT.create();
        //把map的值迭代放入builder中
        map.forEach(builder::withClaim);
        //整合过期时间
        builder.withExpiresAt(date);
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }

    //解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).toString();
        String identity=verify.getClaim(JWT_KEY_IDENTITY).toString();
        TokenResult tokenResult=new TokenResult();
        tokenResult.setIdentity(identity);
        tokenResult.setPhone(phone);
        return tokenResult;
    }

    public static void main(String[] args) {
        String s = generatorToken("15666015992","1");
        System.out.println("生成的token:\n"+s);
        TokenResult tokenResult = parseToken(s);
        System.out.println("解析的 token: \n"+tokenResult);
    }

}
