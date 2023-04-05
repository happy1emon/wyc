package com.xg.servicemap.remote;

import com.xg.internalcommon.constant.AmapConfigConstants;
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

    public ResponseResult add(String name,Long desc){
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


    public ResponseResult aroundSearch(String center,String radius){
        StringBuilder url=new StringBuilder(AmapConfigConstants.TERMINAL_SEARCH_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+amapSid);
        url.append("&");
        url.append("center="+center);
        url.append("&");
        url.append("radius=").append(radius);

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray result = data.getJSONArray("results");
        ArrayList<TerminalResponse> res=new ArrayList<>();
        for(int i=0;i<result.size();i++){
            JSONObject jsonObject1 = result.getJSONObject(i);
            Long carId = jsonObject1.getLong("desc");
            String tid=jsonObject1.getString("tid");
            TerminalResponse terminalResponse=new TerminalResponse();
            terminalResponse.setDesc(carId);
            terminalResponse.setTid(tid);
            terminalResponse.setSid(amapSid);
            res.add(terminalResponse);
        }
        return ResponseResult.success(res);
    }



}
