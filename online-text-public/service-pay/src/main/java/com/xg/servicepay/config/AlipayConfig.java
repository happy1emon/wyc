package com.xg.servicepay.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @USER: XGGG
 * @DATE: 2023/4/13
 */
@Component
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayConfig {
    private String appId;
    private String appPrivateKey;
    private String publicKey;
    private String notifyUrl;

    //初始化
    @PostConstruct
    public void init() {
        //基础配置
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = "RSA2";

        //业务配置
        config.appId = this.appId;
        config.merchantPrivateKey = this.appPrivateKey;
        config.alipayPublicKey = this.publicKey;
        config.notifyUrl = this.notifyUrl;
        //用ali的Factory
        Factory.setOptions(config);
        System.out.println("支付宝配置初始化完成");
    }


}
