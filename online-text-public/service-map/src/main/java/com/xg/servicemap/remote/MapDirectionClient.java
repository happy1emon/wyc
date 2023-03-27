package com.xg.servicemap.remote;

import com.sun.javafx.scene.traversal.Direction;
import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude,
                                       String depLatitude,
                                       String destLongitude,
                                       String destLatitude) {

        //1 组装参数 调用url
        //https://restapi.amap.com/v3/direction/walking
        // ?origin=116.434307,39.90909
        // &destination=116.434446,39.90816
        // &output=json&key=08449262cb3620f7487990f953bb3ebd
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin=" + depLongitude + "," + depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination=" + destLongitude + "," + destLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extentions=base");
        urlBuilder.append("&");
        urlBuilder.append("ouput=json");
        urlBuilder.append("&");
        urlBuilder.append("key=" + amapKey);
        System.out.println(urlBuilder);
        //2 调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info(directionString);
        //3 解析接口
        DirectionResponse directionResponse = parseDirectionEntity(directionString);
        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String directionString) {
        DirectionResponse directionResponse = null;
        //处理异常
        try {
            JSONObject result = JSONObject.fromObject(directionString);
            if (result.has(AmapConfigConstants.STATUS)){
                int status = result.getInt(AmapConfigConstants.STATUS);
                if (status == 1) {
                    if (result.has(AmapConfigConstants.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathArray.getJSONObject(0);
                        directionResponse=new DirectionResponse();
                        if (pathObject.has(AmapConfigConstants.DISTANCE)){
                            int distance=pathObject.getInt(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if (pathObject.has(AmapConfigConstants.DURATION)){
                            int duration=pathObject.getInt(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return directionResponse;
    }


}
