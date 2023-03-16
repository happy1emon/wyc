package com.xg.servicemap.service;

import com.xg.internalcommon.dto.ResponseResult;

public interface DirecationService {

    ResponseResult driving(String depLongitude, String depLatitude,String destLongitude,String destLatitude);
}
