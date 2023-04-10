package com.xg.servicedriveruser.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicedriveruser.service.CityDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/4/10
 */

@RestController
@RequestMapping("/city-driver")
public class CityDriverController {

    @Autowired
    private CityDriverService cityDriverService;

    /**
     * 查询当前城市有没有可以用的司机
     * @param cityCode 城市代码
     * @return boolean
     */
    @GetMapping("/is-available-driver")
    public ResponseResult<Boolean> isAvailableDriver(@RequestParam String cityCode){

        return  cityDriverService.isAvailableCount(cityCode);
    }

}
