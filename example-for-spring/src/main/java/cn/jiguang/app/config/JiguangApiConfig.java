package cn.jiguang.app.config;

import cn.jiguang.sdk.api.*;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
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

    // sdk默认使用的feign-okhttp，下面是设置示例
    // 更多okhttp配置请参考：https://square.github.io/okhttp/5.x/okhttp/okhttp3/-ok-http-client/-builder/index.html
    @Bean("okHttpClient")
    public OkHttpClient okHttpClient() {
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient().newBuilder()
                // .proxy() // 设置代理，如果有需要
                .connectTimeout(5, TimeUnit.SECONDS) // 设置连接超时
                .build();
        OkHttpClient okHttpClient1 = new OkHttpClient(okHttpClient);
        log.info("okHttpClient1:{}", okHttpClient1);
        return okHttpClient1;
    }

    @Bean
    public PushApi pushApi(@Qualifier("okHttpClient") OkHttpClient okHttpClient) {
        return new PushApi.Builder()
                .setClient(okHttpClient) // 如果不配置client，则使用默认的okHttpClient
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
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
