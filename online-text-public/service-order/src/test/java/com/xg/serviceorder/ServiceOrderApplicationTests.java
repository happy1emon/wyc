package com.xg.serviceorder;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.internalcommon.dto.OrderInfo;
import com.xg.serviceorder.mapper.OrderInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ServiceOrderApplicationTests {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Test
    public void test(){
        List<Integer> orderGoingOnByDriverId = orderInfoMapper.isOrderGoingOnByDriverId(1641446566823079937l);
        System.out.println(orderGoingOnByDriverId);
    }

}
