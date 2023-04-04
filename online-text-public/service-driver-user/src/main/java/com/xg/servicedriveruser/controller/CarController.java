package com.xg.servicedriveruser.controller;
import com.xg.servicedriveruser.service.CarService;
import com.xg.internalcommon.dto.Car;
import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @USER: XGGG
 * @DATE: 2023/3/29
 */
@RestController
public class CarController {

    @Autowired
    private CarService carService;
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping("/queryCarByCarId")
    public ResponseResult queryCarByCarId(Long carId){
        return carService.queryCarByCarId(carId);
    }


}
