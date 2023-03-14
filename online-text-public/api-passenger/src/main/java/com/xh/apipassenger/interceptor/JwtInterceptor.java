package com.xh.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xg.internalcommon.constant.TokenConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.TokenResult;
import com.xg.internalcommon.utils.JwtUtils;
import com.xg.internalcommon.utils.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//JWT拦截器 但是这里没有初始化stringRedisTemplate 要在config中手动添加
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception {
        //token携带位置一般在请求头的Authorization中
        String token = request.getHeader("Authorization");

        String resultString = "";
        boolean result = true;
        //定义返回结果 和返回信息
        TokenResult tokenResult = JwtUtils.checkToken(token);
        //从redis中取出token
        if (tokenResult != null) {
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            //比较我们传入的token和redis中的token是否相等
            if (StringUtils.isBlank(tokenRedis) || (!token.trim().equals(tokenRedis.trim()))) {
                resultString = "token invalid";
                result = false;
            }
        }
        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return result;
    }
}
