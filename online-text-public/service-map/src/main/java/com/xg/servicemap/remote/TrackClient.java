package com.xg.servicemap.remote;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TrackResponse;
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
public class TrackClient {
    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult<TrackResponse> add(String tid){
        StringBuilder url=new StringBuilder(AmapConfigConstants.TRACK_ADD_URL);
        url.append("?");
        url.append("key=").append(amapKey);
        url.append("&");
        url.append("sid=").append(amapSid);
        url.append("&");
        url.append("tid=").append(tid);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = stringResponseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        //轨迹id
        String trid = data.getString("trid");

        TrackResponse tr=new TrackResponse();
        tr.setTrid(trid);
        if (data.has("trname")){
            tr.setTrname(data.getString("trname"));
        }
        return ResponseResult.success(tr);


    }



}
