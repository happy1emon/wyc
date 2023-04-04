package com.xg.internalcommon.response;

import lombok.Data;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@Data
public class TerminalResponse {
    /**
     * 终端名称
     */
    private String name;
    /**
     * 终端所在服务id
     */
    private String sid;
    /**
     * 终端id
     */
    private String tid;
    /**
     * 服务描述
     */
    private String desc;
}
