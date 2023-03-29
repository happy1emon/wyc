
package com.xg.servicedriveruser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.ResponseResult;

/**
* @author junxuan
* @description 针对表【car】的数据库操作Service
* @createDate 2023-03-29 15:37:33
*/
public interface CarService extends IService<Car> {

    ResponseResult addCar(Car car);
}
