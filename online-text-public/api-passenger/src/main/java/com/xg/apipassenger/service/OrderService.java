package com.xg.apipassenger.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;

/**
 * @USER: XGGG
 * @DATE: 2023/4/5
 */
public interface OrderService {

    ResponseResult add(OrderRequest orderRequest);

    String testClucherOrder(Long orderId);

    ResponseResult cancel(Long orderId);
}
