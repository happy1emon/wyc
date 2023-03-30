package com.xg.apidriver.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.NumberCodeResponse;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
public interface VerificationCodeService {

    ResponseResult<NumberCodeResponse> checkAndSendVerificationCode(String driverPhone);

    ResponseResult checkVerificationCode(String driverPhone, String verificationCode, String driverIdentity);
}
