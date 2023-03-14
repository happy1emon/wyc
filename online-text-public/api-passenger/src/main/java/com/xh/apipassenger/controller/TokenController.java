package com.xh.apipassenger.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.TokenResult;
import com.xg.internalcommon.response.TokenResponse;
import com.xh.apipassenger.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的refreshToken" + refreshTokenSrc);
        return tokenService.refreshToken(refreshTokenSrc);
    }

}
