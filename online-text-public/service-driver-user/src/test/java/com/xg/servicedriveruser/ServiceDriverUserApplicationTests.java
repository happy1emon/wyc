package com.xg.servicedriveruser;

import com.xg.internalcommon.response.OrderDriverResponse;
import com.xg.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.xg.servicedriveruser.mapper.DriverUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ServiceDriverUserApplicationTests {
    @Autowired
    DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Test
    void contextLoads() {
        Integer integer = driverCarBindingRelationshipMapper.selectWorkStatus("110000");
        System.out.println(integer);
    }
    @Autowired
    DriverUserMapper driverUserMapper;

    @Test
    void testMapper(){
        List<OrderDriverResponse> availableDriver = driverUserMapper.getAvailableDriver(1643520325734268929l);
        System.out.println(availableDriver);
    }

}
