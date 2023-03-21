package com.xg.servicemap.service.impl;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicemap.service.DictDistrictService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DictDistrictServiceImpl implements DictDistrictService {

    @Value("${amap.key}")
    private String amapKey;

    @Override
    public ResponseResult initDictDistrict(String keywords) {
        //https://restapi.amap.com/v3/config/district
        // ?key=08449262cb3620f7487990f953bb3ebd
        // &keywords=%E5%B1%B1%E4%B8%9C
        // &subdistrict=2
        // &extensions=base
        //拼接请求url
        StringBuilder url=new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("keywords="+keywords);
        url.append("&");
        url.append("subdistrict=3");



        return ResponseResult.success();
    }
}
