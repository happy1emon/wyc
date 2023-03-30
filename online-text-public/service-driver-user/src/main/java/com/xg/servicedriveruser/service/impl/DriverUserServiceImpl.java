package com.xg.servicedriveruser.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.DriverCarConstants;
import com.xg.internalcommon.constant.DriverWorkStatusConstants;
import com.xg.internalcommon.dto.DriverUserWorkStatus;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.DriverUser;
import com.xg.servicedriveruser.mapper.DriverUserMapper;
import com.xg.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import com.xg.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


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

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    @Override
    public ResponseResult testGetDriverUser() {
        DriverUser driverUser = driverUserMapper.selectById(2);
        return ResponseResult.success(driverUser);
    }

    @Override
    public ResponseResult addDriverUser(DriverUser driverUser) {
        driverUser.setGmtCreate(LocalDateTime.now());
        driverUser.setGmtModified(LocalDateTime.now());
        int insert = driverUserMapper.insert(driverUser);
        //初始化司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus=new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverWorkStatusConstants.OFFLINE);
        driverUserWorkStatus.setGmtCreate(LocalDateTime.now());
        driverUserWorkStatus.setGmtModified(LocalDateTime.now());
        insert+=driverUserWorkStatusMapper.insert(driverUserWorkStatus);

        if (insert == 2) {
            return ResponseResult.success();
        }
        return ResponseResult.fail(CommonStatusEnum.FAIL);
    }

    @Override
    public ResponseResult updateDriverUser(DriverUser driverUser) {
        LocalDateTime now=LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<DriverUser> queryDriverPhone(String driverPhone) {
        QueryWrapper<DriverUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("driver_phone",driverPhone);
        queryWrapper.eq("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectList(queryWrapper);
        if(driverUsers.size()==0){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(),
                    CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        DriverUser driverUser=driverUsers.get(0);
        return ResponseResult.success(driverUser);
    }
}




