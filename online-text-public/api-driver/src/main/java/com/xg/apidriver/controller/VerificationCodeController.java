package com.xg.apidriver.controller;

import com.xg.apidriver.service.VerificationCodeService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/3/30
 */
@RestController
@Slf4j
public class VerificationCodeController {


    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String driverPhone= verificationCodeDTO.getDriverPhone();
        log.info("司机的号码: "+driverPhone);
        return verificationCodeService.checkAndSendVerificationCode(driverPhone);
    }

}
