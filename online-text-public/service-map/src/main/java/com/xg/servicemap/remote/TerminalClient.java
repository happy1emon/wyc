package com.xg.servicemap.remote;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TerminalResponse;
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
public class TerminalClient {
    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult add(String name){
        StringBuilder url=new StringBuilder(AmapConfigConstants.TERMINAL_ADD_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+amapSid);
        url.append("&");
        url.append("name="+name);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String tid = data.getString("tid");
        TerminalResponse terminalResponse=new TerminalResponse();
        terminalResponse.setName(name);
        terminalResponse.setSid(amapSid);
        terminalResponse.setTid(tid);
        return ResponseResult.success(terminalResponse);
    }



}
