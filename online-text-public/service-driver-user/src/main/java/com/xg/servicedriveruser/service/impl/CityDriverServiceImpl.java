package com.xg.servicedriveruser.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.xg.servicedriveruser.service.CityDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: XGGG
 * @DATE: 2023/4/10
 */
@Service
public class CityDriverServiceImpl implements CityDriverService {
    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Override
    public ResponseResult isAvailableCount(String cityCode) {
        Integer integer = driverCarBindingRelationshipMapper.selectWorkStatus(cityCode);
        if (integer>0){
            return ResponseResult.success(true);
        }
        return ResponseResult.success(false);
    }
}
