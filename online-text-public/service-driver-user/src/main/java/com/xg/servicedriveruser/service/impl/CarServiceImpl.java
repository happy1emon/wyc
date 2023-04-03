package com.xg.servicedriveruser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sun.xml.internal.bind.v2.model.core.EnumConstant;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TerminalResponse;
import com.xg.servicedriveruser.mapper.CarMapper;
import com.xg.servicedriveruser.remote.ServiceMapClient;
import com.xg.servicedriveruser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author junxuan
* @description 针对表【car】的数据库操作Service实现
* @createDate 2023-03-29 15:37:33
*/
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Override
    public ResponseResult addCar(Car car) {
        LocalDateTime now=LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo());
        String tid = responseResult.getData().getTid();
        if (tid==null){
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(),CommonStatusEnum.FAIL.getValue());
        }
        car.setTid(tid);

        // 获得此车辆的轨迹id :trid

        int insert = carMapper.insert(car);
        if (insert==1){
            return ResponseResult.success("car--Binding: "+car.getVehicleNo()+"-"+ tid+" success!");
        }
        return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(),CommonStatusEnum.FAIL.getValue());
    }
}




