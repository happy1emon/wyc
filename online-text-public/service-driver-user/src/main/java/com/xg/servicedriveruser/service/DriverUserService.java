package com.xg.servicedriveruser.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.DriverUser;
import com.xg.internalcommon.response.OrderDriverResponse;

import java.util.ArrayList;

/**
* @author junxuan
* @description 针对表【driver_user】的数据库操作Service
* @createDate 2023-03-28 12:26:09
*/
public interface DriverUserService extends IService<DriverUser> {

    ResponseResult testGetDriverUser();
    ResponseResult addDriverUser(DriverUser driverUser);
    ResponseResult updateDriverUser(DriverUser driverUser);
    ResponseResult<DriverUser> queryDriverPhone(String driverPhone);
    ResponseResult<OrderDriverResponse> getAvailableDriver(Long carId);

}
