package com.xg.apidriver.service;

import com.xg.internalcommon.dto.ResponseResult;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
public interface VerificationCodeService {

    ResponseResult checkAndSendVerificationCode(String driverPhone);

}
