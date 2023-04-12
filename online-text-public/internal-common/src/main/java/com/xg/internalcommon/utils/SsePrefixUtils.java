package com.xg.internalcommon.utils;

/**
 * @USER: XGGG
 * @DATE: 2023/4/12
 */
public class SsePrefixUtils {
    public static final String SPERATOR = "$";

    public static String generatorSseKey(Long userId, String identity) {
        return userId + SPERATOR + identity;
    }


}
