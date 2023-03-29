package com.xg.servicedriveruser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.constant.DriverCarConstants;
import com.xg.internalcommon.dto.DriverCarBindingRelationship;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.xg.servicedriveruser.service.DriverCarBindingRelationshipService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
* @author junxuan
* @description 针对表【driver_car_binding_relationship】的数据库操作Service实现
* @createDate 2023-03-29 17:08:54
*/
@Service
public class DriverCarBindingRelationshipServiceImpl extends ServiceImpl<DriverCarBindingRelationshipMapper, DriverCarBindingRelationship>
    implements DriverCarBindingRelationshipService {

   @Autowired
   private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Override
    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {

        //判断，如果参数中的车辆和司机，已经做过绑定，则不允许再次绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        Integer count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count.intValue()>0){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getCode(),
                    CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getValue());
        }
        //车辆已被绑定过
        queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        count=driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count.intValue()>0){
            return ResponseResult.fail(CommonStatusEnum.CAR_HAS_BOUND_ERORR.getCode(),
                    CommonStatusEnum.CAR_HAS_BOUND_ERORR.getValue());
        }
        //司机已有绑定车辆
        queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        count=driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count.intValue()>0){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_HAS_BOUND_ERORR.getCode(),
                    CommonStatusEnum.DRIVER_HAS_BOUND_ERORR.getValue());
        }

        LocalDateTime now =LocalDateTime.now();
        driverCarBindingRelationship.setBindingTime(now);
        driverCarBindingRelationship.setBindState(DriverCarConstants.DRIVER_CAR_BIND);
        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);

        return ResponseResult.success("");
    }

    @Override
    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        Map<String,Object> map=new HashMap<>();
        map.put("driver_id",driverCarBindingRelationship.getDriverId());
        map.put("car_id",driverCarBindingRelationship.getCarId());
        map.put("bind_state",DriverCarConstants.DRIVER_CAR_BIND);

        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        //无绑定关系错误
        if (driverCarBindingRelationships.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXIST.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXIST.getValue());
        }
        DriverCarBindingRelationship relationship=driverCarBindingRelationships.get(0);
        relationship.setBindState(DriverCarConstants.DRIVER_CAT_UNBIND);
        LocalDateTime now=LocalDateTime.now();
        relationship.setUnBindingTime(now);
        driverCarBindingRelationshipMapper.updateById(relationship);
        return ResponseResult.success("");
    }
}




