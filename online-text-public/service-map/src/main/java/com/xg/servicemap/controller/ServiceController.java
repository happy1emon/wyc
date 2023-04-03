package com.xg.servicemap.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicemap.service.ServiceFromMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
//服务管理控制器
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceFromMapService serviceFromMapService;
    /**
     * 创建服务
     * @param name 服务名称
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(String name){
        return serviceFromMapService.add(name);
    }


}
