package cn.jiguang.app.config;

import cn.jiguang.sdk.api.*;
import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JiguangApiConfig {

    @Value("${jiguang.api.app-key}")
    private String appKey;

    @Value("${jiguang.api.master-secret}")
    private String masterSecret;

    @Value("${jiguang.api.dev-key}")
    private String devKey;

    @Value("${jiguang.api.dev-secret}")
    private String devSecret;

    @Value("${jiguang.api.group-key}")
    private String groupKey;

    @Value("${jiguang.api.group-master-secret}")
    private String groupMasterSecret;

    @Bean
    public PushApi pushApi() {
        return new PushApi.Builder()
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .build();
    }

    @Bean
    public DeviceApi deviceApi() {
        return new DeviceApi.Builder()
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .build();
    }

    @Bean
    public ReportApi reportApi() {
        return new ReportApi.Builder()
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .build();
    }

    @Bean
    public AdminApi adminApi() {
        return new AdminApi.Builder()
                .setDevKey(devKey)
                .setDevSecret(devSecret)
                .build();
    }

    @Bean
    public GroupPushApi groupPushApi() {
        return new GroupPushApi.Builder()
                .setGroupKey(groupKey)
                .setGroupMasterSecret(groupMasterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();
    }

}
