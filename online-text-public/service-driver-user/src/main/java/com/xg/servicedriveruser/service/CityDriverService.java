package com.xg.servicedriveruser.service;

import com.xg.internalcommon.dto.ResponseResult;

import javax.annotation.Resource;

/**
 * @USER: XGGG
 * @DATE: 2023/4/10
 */
public interface CityDriverService {


    ResponseResult<Boolean> isAvailableCount(String cityCode);
}
