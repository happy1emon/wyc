package com.xg.apidriver.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;

/**
 * @USER: XGGG
 * @DATE: 2023/4/12
 */
public interface OrderInfoService {


    ResponseResult toPickUpPassenger(OrderRequest orderRequest);
}
