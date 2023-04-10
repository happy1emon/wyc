package com.xg.servicemap.service.impl;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TerminalResponse;
import com.xg.servicemap.remote.TerminalClient;
import com.xg.servicemap.service.TerminalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@Service
@Slf4j
public class TerminalServiceImpl implements TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    @Override
    public ResponseResult add(String name, Long desc) {
        return terminalClient.add(name,desc);
    }

    @Override
    public ResponseResult<ArrayList<TerminalResponse>> aroundSearch(String center, String radius) {

        return terminalClient.aroundSearch(center,radius);
    }
}
