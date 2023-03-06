package com.xg.internalcommon.request;

import lombok.Data;

@Data
public class VerificationCodeDTO {
    private  String passengerPhone;
    private String verificationCode;
}
