package com.xg.servicemap.remote;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.response.DirectionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;


    public DirectionResponse direction(String depLongitude,
                                       String depLatitude,
                                       String destLongitude,
                                       String destLatitude){

        //1 组装参数 调用url
        //https://restapi.amap.com/v3/direction/walking
        // ?origin=116.434307,39.90909
        // &destination=116.434446,39.90816
        // &output=json&key=08449262cb3620f7487990f953bb3ebd
        StringBuilder urlBuilder=new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin="+depLongitude+","+depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination="+destLongitude+","+destLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extentions=base");
        urlBuilder.append("&");
        urlBuilder.append("ouput=json");
        urlBuilder.append("&");
        urlBuilder.append("key="+amapKey);
        System.out.println(urlBuilder);
        //2 调用高德接口

        //3 解析接口

        return null;
    }

}
