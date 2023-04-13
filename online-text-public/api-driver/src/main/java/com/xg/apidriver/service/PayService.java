package com.xg.apidriver.service;

import com.xg.internalcommon.dto.ResponseResult;

/**
 * @USER: XGGG
 * @DATE: 2023/4/13
 */
public interface PayService {

    ResponseResult pushPayInfo(Long orderId, Double price,Long passengerId);

}
