package com.xg.servicemap.remote;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TerminalResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@Service
public class TerminalClient {
    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult<TerminalResponse> add(String name,Long desc){
        StringBuilder url=new StringBuilder(AmapConfigConstants.TERMINAL_ADD_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+amapSid);
        url.append("&");
        url.append("name="+name);
        url.append("&");
        url.append("desc=").append(desc);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String tid = data.getString("tid");
        TerminalResponse terminalResponse=new TerminalResponse();
        terminalResponse.setName(name);
        terminalResponse.setSid(amapSid);
        terminalResponse.setTid(tid);
        terminalResponse.setDesc(desc);
        return ResponseResult.success(terminalResponse);
    }


    public ResponseResult<ArrayList<TerminalResponse>> aroundSearch(String center,String radius){
        StringBuilder url=new StringBuilder(AmapConfigConstants.TERMINAL_SEARCH_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+amapSid);
        url.append("&");
        url.append("center="+center);
        url.append("&");
        url.append("radius=").append(radius);

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString( ),null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        int count = data.getInt("count");
        if (count==0){
            return ResponseResult.success(new ArrayList<>());
        }

        JSONArray result = data.getJSONArray("results");
        ArrayList<TerminalResponse> res=new ArrayList<>();
        for(int i=0;i<result.size();i++){
            JSONObject jsonObject1 = result.getJSONObject(i);
            Long carId =Long.parseLong(jsonObject1.getString("desc"));
            String tid=jsonObject1.getString("tid");
            String name = jsonObject1.getString("name");
            JSONObject location = jsonObject1.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");
            TerminalResponse terminalResponse=new TerminalResponse();
            terminalResponse.setDesc(carId);
            terminalResponse.setTid(tid);
            terminalResponse.setSid(amapSid);
            terminalResponse.setLatitude(latitude);
            terminalResponse.setLongitude(longitude);
            terminalResponse.setName(name);
            res.add(terminalResponse);
        }
        return ResponseResult.success(res);
    }



}
