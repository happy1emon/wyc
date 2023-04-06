package com.xg.serviceorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.serviceorder.dto.Order;
import com.xg.serviceorder.service.OrderService;
import com.xg.serviceorder.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author junxuan
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-04-06 13:27:51
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




