package com.xg.serviceorder.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @USER: XGGG
 * @DATE: 2023/4/11
 */
@Component
public class RedisConfig {

    private final String potocol = "redis://";

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;


    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(potocol + redisHost + ":" + redisPort).setPassword("123").setDatabase(0);
        return Redisson.create(config);
    }
}
