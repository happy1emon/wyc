package com.xg.servicemap.service;

import com.xg.internalcommon.dto.ResponseResult;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
public interface ServiceFromMapService {
    /**
     * 创建服务
     * @param name 服务名称
     * @return
     */
    ResponseResult add(String name);

}
