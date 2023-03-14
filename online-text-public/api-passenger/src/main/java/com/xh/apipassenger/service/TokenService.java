package com.xh.apipassenger.service;

import com.xg.internalcommon.dto.ResponseResult;

public interface TokenService {

    ResponseResult refreshToken(String refreshTokenSrc);
}
