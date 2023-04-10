package com.xg.servicedriveruser;

import com.xg.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceDriverUserApplicationTests {
    @Autowired
    DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Test
    void contextLoads() {
        Integer integer = driverCarBindingRelationshipMapper.selectWorkStatus("110000");
        System.out.println(integer);
    }

}
