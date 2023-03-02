package com.xg.serviceverficationcode.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.serviceverficationcode.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {
    @Autowired
    private VerificationService service;
    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        return service.generateRandom(size);
    }
}
