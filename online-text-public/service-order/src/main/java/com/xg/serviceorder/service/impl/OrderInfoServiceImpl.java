package com.xg.serviceorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.internalcommon.constant.OrderConstants;
import com.xg.internalcommon.dto.OrderInfo;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.OrderRequest;
import com.xg.serviceorder.service.OrderInfoService;
import com.xg.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author junxuan
* @description 针对表【order_info】的数据库操作Service实现
* @createDate 2023-04-06 13:35:18
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService{

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public ResponseResult add(OrderRequest orderRequest) {
        OrderInfo orderInfo=new OrderInfo();
        BeanUtils.copyProperties(orderRequest,orderInfo);
        //设置状态
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        //设置时间
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);



        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success("");
    }
}




