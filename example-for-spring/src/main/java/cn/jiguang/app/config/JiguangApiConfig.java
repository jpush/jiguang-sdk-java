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

    @Value("${jiguang.api.host}")
    private String host;

    @Value("${jiguang.api.app-key}")
    private String appKey;

    @Value("${jiguang.api.master-secret}")
    private String masterSecret;

    // sdk默认使用的feign-okhttp，下面是设置示例
    // 更多okhttp配置请参考：https://square.github.io/okhttp/5.x/okhttp/okhttp3/-ok-http-client/-builder/index.html
    @Bean("okHttpClient")
    public OkHttpClient okHttpClient() {
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient().newBuilder()
                // .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy_host", proxy_port))) // 设置代理，如果有需要
                .connectTimeout(5, TimeUnit.SECONDS) // 设置连接超时
                .build();
        OkHttpClient client = new OkHttpClient(okHttpClient);
        log.info("okHttpClient:{}", client);
        return client;
    }

    @Bean
    public PushApi pushApi(@Qualifier("okHttpClient") OkHttpClient okHttpClient) {
        return new PushApi.Builder()
                .setHost(host)
                .setClient(okHttpClient) // 如果不配置client，则使用默认的okHttpClient
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
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
