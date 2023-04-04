package com.xg.apidriver.service.impl;

import com.xg.apidriver.remote.ServiceDriverUserClient;
import com.xg.apidriver.remote.ServiceMapClient;
import com.xg.apidriver.service.PointService;
import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.ApiDriverPointRequest;
import com.xg.internalcommon.request.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/4/4
 */
@Service
public class PointServiceImpl implements PointService {


    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Override
    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest) {
        //1 获取carId
        Long carId = apiDriverPointRequest.getCarId();
        //2 通过carId获取 tid trid 调用Service-driver-user的接口 查询
        ResponseResult<Car> carResponseResult = serviceDriverUserClient.queryCarByCarId(carId);
        Car car = carResponseResult.getData();
        String tid = car.getTid();
        String trid=car.getTrid();
        //3 调用地图上传
        PointRequest pointRequest=new PointRequest();
        pointRequest.setTid(tid);
        pointRequest.setTrid(trid);
        pointRequest.setPoints(apiDriverPointRequest.getPoints());
        return serviceMapClient.upload(pointRequest);
    }
}
