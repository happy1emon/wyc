package com.xg.servicemap.remote;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapDicDistrictClient {
    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public String DictDistrict(String keywords) {
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

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);


        return forEntity.getBody();
    }




}
