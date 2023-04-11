package com.xg.serviceorder.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @USER: XGGG
 * @DATE: 2023/4/11
 */
@Component
public class RedisConfig {

    @Bean
    public RedissonClient  redissonClient(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("123").setDatabase(0);

        return Redisson.create(config);
    }
}
