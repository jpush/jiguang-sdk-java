package cn.jiguang.app.config;

import cn.jiguang.sdk.api.*;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.okhttp.OkHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

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

    @Bean
    public PushApi pushApi() {
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient().newBuilder()
                // 自定义proxy，其他设置参考https://square.github.io/okhttp/5.x/okhttp/okhttp3/-ok-http-client/-builder/index.html
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy_host", 8000)))
                .build();

        return new PushApi.Builder()
                // .setClient(new feign.okhttp.OkHttpClient(okHttpClient)) // sdk默认使用的feign-okhttp，可自定义，可选
                // .setOptions(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true)) // 可自定义超时参数，可选
                // .setRetryer(new Retryer.Default(100, SECONDS.toMillis(1), 5)) // 可自定义重试参数，可选
                // .setLoggerLevel(Logger.Level.FULL) // 可自定义日志打印级别，可选
                .setAppKey(appKey) // 必填
                .setMasterSecret(masterSecret) // 必填
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
