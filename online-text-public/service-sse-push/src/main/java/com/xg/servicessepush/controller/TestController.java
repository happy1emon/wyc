package com.xg.servicessepush.controller;

import com.xg.internalcommon.utils.SsePrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @USER: XGGG
 * @DATE: 2023/4/12
 */
@RestController
@Slf4j
public class TestController {

    public static Map<String, SseEmitter> SseEmitterMap = new HashMap<>();

    /**
     * @param userId
     * @param identity
     * @return
     */
    @GetMapping("/connect")
    public SseEmitter connect(@RequestParam Long userId,@RequestParam String identity) {
        log.info("用户ID：" + userId + ",身份类别:" + identity);
        //设置连接永不超时
        SseEmitter sseEmitter = new SseEmitter(0L);
        String ssemapKey = SsePrefixUtils.generatorSseKey(userId, identity);
        SseEmitterMap.put(ssemapKey,sseEmitter);
        //注册监听
//        SseEmitterMap.put(driverId,sseEmitter);
        return sseEmitter;


    }

    /**
     * 发送消息
     *
     * @param userId 消息接收者
     * @param identity 身份
     * @param content  消息内容
     * @return
     */
    @GetMapping("/push")
    public String push(@RequestParam Long userId,@RequestParam String identity, @RequestParam String content) {
        String generatorSseKey = SsePrefixUtils.generatorSseKey(userId, identity);
        try {
            if (SseEmitterMap.containsKey(generatorSseKey))
                SseEmitterMap.get(generatorSseKey).send(content);
            else
                return "此用户不存在";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "给用户:" + userId + ",发送了消息:" + content;
    }

    /**
     * 关闭连接
     * @param userId 关闭连接的用户
     * @param identity 身份
     * @return 关闭成功
     */
    @GetMapping("/close")
    public String close(@RequestParam Long userId,@RequestParam String identity) {
        String sseKey = SsePrefixUtils.generatorSseKey(userId, identity);
        System.out.println("关闭连接：" + userId);
        if (SseEmitterMap.containsKey(sseKey)) {
            SseEmitterMap.remove(sseKey);
        }
        return "close suceess";
    }


}
