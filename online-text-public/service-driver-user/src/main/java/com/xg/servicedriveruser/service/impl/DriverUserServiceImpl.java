package com.xg.servicedriveruser.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicedriveruser.dto.DriverUser;
import com.xg.servicedriveruser.mapper.DriverUserMapper;
import com.xg.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
* @author junxuan
* @description 针对表【driver_user】的数据库操作Service实现
* @createDate 2023-03-28 12:26:09
*/
@Service
public class DriverUserServiceImpl extends ServiceImpl<DriverUserMapper, DriverUser>
    implements DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    @Override
    public ResponseResult testGetDriverUser() {
        DriverUser driverUser=driverUserMapper.selectById(2);
        return ResponseResult.success(driverUser);
    }
}




