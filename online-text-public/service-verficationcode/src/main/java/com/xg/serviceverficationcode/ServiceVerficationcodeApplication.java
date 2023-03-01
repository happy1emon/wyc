package com.xg.serviceverficationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVerficationcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerficationcodeApplication.class, args);
    }

}
