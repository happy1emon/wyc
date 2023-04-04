package com.xg.servicemap.remote;

import com.sun.xml.internal.ws.api.FeatureListValidatorAnnotation;
import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.dto.PointDTO;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.request.PointRequest;
import com.xg.servicemap.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @USER: XGGG
 * @DATE: 2023/4/4
 */
@Service
public class PointClient {
    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult upload(PointRequest pointRequest){
        StringBuilder url=new StringBuilder(AmapConfigConstants.POINT_UPLOAD);
        url.append("?");
        url.append("key=").append(amapKey);
        url.append("&");
        url.append("sid=").append(amapSid);
        url.append("&");
        url.append("trid=").append(pointRequest.getTrid());
        url.append("&");
        url.append("tid=").append(pointRequest.getTid());
        url.append("&");
        url.append("points=");
        url.append("%5B");
        PointDTO[] points = pointRequest.getPoints();
        for (PointDTO p:points) {
            url.append("%7B");
            String location = p.getLocation();
            String locatetime = p.getLocatetime();
            url.append("%22location%22").append("%3A").append("%22"+location+"%22").append("%2C");
            url.append("%22locatetime%22").append("%3A").append("%22"+locatetime+"%22");
            url.append("%7D");
        }
        url.append("%5D");
        System.out.println("高德地图请求: "+url);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        System.out.println("高德地图响应:"+forEntity.getBody());




        return ResponseResult.success();
    }
}
