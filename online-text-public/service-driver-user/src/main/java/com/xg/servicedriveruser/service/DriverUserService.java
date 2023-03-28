package com.xg.servicedriveruser.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicedriveruser.dto.DriverUser;

/**
* @author junxuan
* @description 针对表【driver_user】的数据库操作Service
* @createDate 2023-03-28 12:26:09
*/
public interface DriverUserService extends IService<DriverUser> {

    ResponseResult testGetDriverUser();
}
