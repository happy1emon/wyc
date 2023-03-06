package com.xg.internalcommon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成token和解析token的功能
 */
public class JwtUtils {
    //盐
    private  static final String SIGN="XGLOVEXIAODAN!!@";

    //生成token
    public static String generatorToken(Map<String,String> map){
        //定义token过期时间
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

    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","18");
        String s = generatorToken(map);
        System.out.println("生成的token:"+s);
    }


}
