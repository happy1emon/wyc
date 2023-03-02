package com.xh.apipassenger.utils;

import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.dto.ResponseResult;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;

public class utils {
    public static ResponseResult checkCode(String verificationCode,String codeRedis){
        if (StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        return ResponseResult.success();
    }
}
