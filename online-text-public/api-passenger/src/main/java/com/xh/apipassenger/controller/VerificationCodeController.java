package com.xh.apipassenger.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xh.apipassenger.request.VerificationCodeDTO;
import com.xh.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        return verificationCodeService.generatorCode(verificationCodeDTO.getPassengerPhone());
    }
    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone=verificationCodeDTO.getPassengerPhone();
        String verificationCode=verificationCodeDTO.getVerificationCode();
        System.out.println("passengerPhone:"+passengerPhone);
        System.out.println("verificationCode:"+verificationCode);
        return verificationCodeService.checkVerificationCode(passengerPhone,verificationCode);
    }
}
