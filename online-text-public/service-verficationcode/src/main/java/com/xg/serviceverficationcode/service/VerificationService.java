package com.xg.serviceverficationcode.service;


import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.NumberCodeResponse;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    public ResponseResult generateRandom(int size){
        double mathRandom = (Math.random() * 9 + 1) * (Math.pow(10,size-1));
        int resultInt= (int) mathRandom;
        System.out.println("generate code:"+resultInt);
        // 小数点后移5位
        NumberCodeResponse numberCodeResponse=new NumberCodeResponse();
        numberCodeResponse.setNumberCode(resultInt);
        ResponseResult success = ResponseResult.success(numberCodeResponse);
        return success;
    }


}
