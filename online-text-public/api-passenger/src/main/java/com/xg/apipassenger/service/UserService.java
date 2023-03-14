package com.xg.apipassenger.service;

import com.xg.internalcommon.dto.ResponseResult;

public interface UserService {

    ResponseResult getUserByAccessToken(String accessToken);
}
