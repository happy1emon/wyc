package com.xg.servicemap.service;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.response.TerminalResponse;
import com.xg.internalcommon.response.TrsearchResponse;

import java.util.ArrayList;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */

public interface TerminalService {

    ResponseResult add(String terminalName,Long desc);

    ResponseResult<ArrayList<TerminalResponse>> aroundSearch(String center, String radius);

    ResponseResult<TrsearchResponse> trsearch(String tid, Long starttime, Long endtime);
}
