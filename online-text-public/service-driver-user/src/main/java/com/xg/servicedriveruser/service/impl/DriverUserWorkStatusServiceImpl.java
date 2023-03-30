package com.xg.servicedriveruser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.DriverWorkStatusConstants;
import com.xg.internalcommon.dto.DriverUserWorkStatus;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicedriveruser.service.DriverUserWorkStatusService;
import com.xg.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author junxuan
* @description 针对表【driver_user_work_status】的数据库操作Service实现
* @createDate 2023-03-30 21:53:42
*/
@Service
public class DriverUserWorkStatusServiceImpl extends ServiceImpl<DriverUserWorkStatusMapper, DriverUserWorkStatus>
    implements DriverUserWorkStatusService{

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    @Override
    public ResponseResult changeWorkStatus(Long driverId, Integer workStatus) {
        LocalDateTime now=LocalDateTime.now();
        UpdateWrapper<DriverUserWorkStatus> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("driver_id",driverId);
        DriverUserWorkStatus driverUserWorkStatus=new DriverUserWorkStatus();
        driverUserWorkStatus.setWorkStatus(workStatus);
        driverUserWorkStatus.setDriverId(driverId);
        driverUserWorkStatus.setGmtModified(now);
        int update = driverUserWorkStatusMapper.update(driverUserWorkStatus,updateWrapper);
        if (update==0){
            return ResponseResult.fail(CommonStatusEnum.FAIL);
        }
        return ResponseResult.success("");
    }
}




