package com.xh.apipassenger.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xh.apipassenger.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){
        //从http请求中获取accessToken
        String accessToken=request.getHeader("Authorization");
        //根据accessToken查询
        return userService.getUserByAccessToken(accessToken);
    }


}
