package com.xg.servicemap.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TrackResponse;
import com.xg.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @PostMapping("/add")
    public ResponseResult<TrackResponse> addTrack(String tid){
        return trackService.add(tid);
    }

}
