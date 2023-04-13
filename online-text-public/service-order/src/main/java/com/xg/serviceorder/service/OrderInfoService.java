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
    /**
     * 创建订单
     * @param orderRequest 请求参数
     * @return  是否成功
     */
    ResponseResult add(OrderRequest orderRequest);

    /**
     * 实时派发订单
     * @param orderInfo
     * @return 是否派单成功
     */
    Boolean dispatchRealTimeOrder(OrderInfo orderInfo);


    ResponseResult toPickUpPassenger(OrderRequest orderRequest);


    ResponseResult arrivedDeparture(OrderRequest orderRequest);

    ResponseResult PickUpPassenger(OrderRequest orderRequest);

    ResponseResult passengerGetoff(OrderRequest orderRequest);

    ResponseResult pay(OrderRequest orderRequest);
}
