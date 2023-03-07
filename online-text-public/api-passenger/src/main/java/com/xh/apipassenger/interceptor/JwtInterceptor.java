package com.xh.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.utils.JwtUtils;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//JWT拦截器
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception {
        //token携带位置一般在请求头的Authorization中
        String token = request.getHeader("Authorization");
        //定义返回结果 和返回信息
        boolean result=true;
        String resultString="";
        try{
        JwtUtils.parseToken(token);
        }
        catch (SignatureVerificationException e){
            //SIGN错误 添加返回信息
            resultString="token sign erro";
            result=false;
        }catch(TokenExpiredException e){
            //token过期
            resultString="token timeout!";
            result = false;
        }catch (AlgorithmMismatchException e){
            //算法异常
            resultString="token AlgorithmMismatchException";
            result=false;
        }catch (Exception e){
            resultString="token invalid";
            result=false;
        }
        if (!result){
            PrintWriter out=response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return result;
    }
}
