package com.xg.serviceorder;

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
        List<Integer> valid = orderInfoMapper.isValid(4L);
        System.out.println(valid);

    }

}
