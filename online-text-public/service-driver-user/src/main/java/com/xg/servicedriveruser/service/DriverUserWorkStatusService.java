package com.xg.servicedriveruser.service;

import com.xg.internalcommon.dto.DriverUserWorkStatus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.internalcommon.dto.ResponseResult;

/**
* @author junxuan
* @description 针对表【driver_user_work_status】的数据库操作Service
* @createDate 2023-03-30 21:53:42
*/
public interface DriverUserWorkStatusService extends IService<DriverUserWorkStatus> {
    ResponseResult changeWorkStatus(Long driverId,Integer workStatus);
}
