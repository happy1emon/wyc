package com.xg.serviceorder.service;

import com.xg.internalcommon.dto.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;

/**
* @author junxuan
* @description 针对表【order_info】的数据库操作Service
* @createDate 2023-04-06 13:35:18
*/
public interface OrderInfoService extends IService<OrderInfo> {

    ResponseResult add(OrderRequest orderRequest);
}
