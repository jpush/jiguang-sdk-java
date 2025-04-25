package cn.jiguang.app.config;

import cn.jiguang.sdk.api.*;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Slf4j
@Configuration
public class JiguangApiConfig {

    @Value("${jiguang.api.host}")
    private String host;

    @Value("${jiguang.api.app-key}")
    private String appKey;

    @Value("${jiguang.api.master-secret}")
    private String masterSecret;

    @Bean
    public PushApi pushApi() {
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient().newBuilder()
                // 自定义proxy，其他设置参考https://square.github.io/okhttp/5.x/okhttp/okhttp3/-ok-http-client/-builder/index.html
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy_host", 8000)))
                .build();

        return new PushApi.Builder()
                // .setClient(new OkHttpClient(okHttpClient)) // sdk默认使用的feign-okhttp，可自定义，可选
                // .setOptions(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true)) // 可自定义超时参数，可选
                // .setRetryer(new Retryer.Default(100, SECONDS.toMillis(1), 5)) // 可自定义重试参数，可选
                // .setLoggerLevel(Logger.Level.FULL) // 可自定义日志打印级别，可选
                .setHost(host) // 必填
                .setAppKey(appKey) // 必填
                .setMasterSecret(masterSecret) // 必填
                .build();
    }

    @Bean
    public ScheduleApi scheduleApi() {
        return new ScheduleApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();
    }

    @Bean
    public DeviceApi deviceApi() {
        return new DeviceApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();
    }

    @Bean
    public ReportApi reportApi() {
        return new ReportApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();
    }

    @Bean
    public GeofenceApi geofenceApi() {
        return new GeofenceApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();
    }

}
