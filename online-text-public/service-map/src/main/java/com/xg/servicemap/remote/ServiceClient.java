package com.xg.servicemap.remote;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.ServiceResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@Service
public class ServiceClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;


    public ResponseResult add(String name){

        StringBuilder url=new StringBuilder();
        url.append(AmapConfigConstants.SERVICE_ADD_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("name="+name);

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String sid = data.getString("sid");
        ServiceResponse response=new ServiceResponse();
        response.setSid(sid);
        return ResponseResult.success(response);
    }



}
