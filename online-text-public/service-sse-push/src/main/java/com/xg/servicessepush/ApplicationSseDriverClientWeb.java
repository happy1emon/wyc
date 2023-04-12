package com.xg.servicessepush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @USER: XGGG
 * @DATE: 2023/4/12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationSseDriverClientWeb {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSseDriverClientWeb.class,args);
    }
}
