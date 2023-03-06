package com.xg.internalcommon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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
    private static final String JWT_KEY="passengerPhone";
    //生成token
    public static String generatorToken(String passengerPhone){
        //定义token过期时间
        Map<String,String> map=new HashMap<>();
        map.put(JWT_KEY,passengerPhone);
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
    public static String parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim passengerPhone = verify.getClaim(JWT_KEY);
        return passengerPhone.toString();
    }

    public static void main(String[] args) {
        String s = generatorToken("15666015992");
        System.out.println("生成的token:\n"+s);
        String s1 = parseToken(s);
        System.out.println("解析的 token: \n"+s1);
    }

}
