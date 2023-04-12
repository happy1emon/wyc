package com.xg.servicemap.remote;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TerminalResponse;
import com.xg.internalcommon.response.TrsearchResponse;
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

    public ResponseResult<TerminalResponse> add(String name, Long desc) {
        StringBuilder url = new StringBuilder(AmapConfigConstants.TERMINAL_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapSid);
        url.append("&");
        url.append("name=" + name);
        url.append("&");
        url.append("desc=").append(desc);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String tid = data.getString("tid");
        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setName(name);
        terminalResponse.setSid(amapSid);
        terminalResponse.setTid(tid);
        terminalResponse.setDesc(desc);
        return ResponseResult.success(terminalResponse);
    }


    public ResponseResult<ArrayList<TerminalResponse>> aroundSearch(String center, String radius) {
        StringBuilder url = new StringBuilder(AmapConfigConstants.TERMINAL_SEARCH_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapSid);
        url.append("&");
        url.append("center=" + center);
        url.append("&");
        url.append("radius=").append(radius);

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        int count = data.getInt("count");
        if (count == 0) {
            return ResponseResult.success(new ArrayList<>());
        }

        JSONArray result = data.getJSONArray("results");
        ArrayList<TerminalResponse> res = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            JSONObject jsonObject1 = result.getJSONObject(i);
            Long carId = Long.parseLong(jsonObject1.getString("desc"));
            String tid = jsonObject1.getString("tid");
            String name = jsonObject1.getString("name");
            JSONObject location = jsonObject1.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");
            TerminalResponse terminalResponse = new TerminalResponse();
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

    public ResponseResult<TrsearchResponse> trsearch(String tid, Long starttime, Long endtime) {
        StringBuilder url = new StringBuilder(AmapConfigConstants.TERMINAL_TRSEARCH);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapSid);
        url.append("&");
        url.append("tid=" + tid);
        url.append("&");
        url.append("starttime=").append(starttime);
        url.append("&");
        url.append("endtime=").append(endtime);
        System.out.println("高德地图查询轨迹请求：" + url.toString());
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        System.out.println("响应：" + forEntity.getBody());
        JSONObject result = JSONObject.fromObject(forEntity.getBody());
        JSONObject data = result.getJSONObject("data");
        int counts = data.getInt("counts");
        if (counts == 0) {
            return null;
        }
        JSONArray tracks = data.getJSONArray("tracks");
        long driveMile = 0L;
        long driveTime = 0L;
        for (int i = 0; i < tracks.size(); i++) {
            JSONObject jsonObject = tracks.getJSONObject(i);
            long distance = jsonObject.getLong("distance");
            driveMile += distance;
            long time = jsonObject.getLong("time");
            driveTime += time;
        }
        driveTime/= (1000*60);//转换成分钟
        TrsearchResponse trsearchResponse=new TrsearchResponse();
        trsearchResponse.setDriveMile(driveMile);
        trsearchResponse.setDriveTime(driveTime);
        return ResponseResult.success(trsearchResponse);


    }
}
