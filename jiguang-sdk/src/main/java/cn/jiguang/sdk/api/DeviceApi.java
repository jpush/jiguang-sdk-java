package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.device.*;
import cn.jiguang.sdk.client.DeviceClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import cn.jiguang.sdk.enums.platform.Platform;
import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

import java.net.Proxy;

public class DeviceApi {

    private final DeviceClient deviceClient;

    protected DeviceApi(@NonNull DeviceClient deviceClient) {
        this.deviceClient = deviceClient;
    }

    public DeviceGetResult getDevice(@NonNull String registrationId) {
        return deviceClient.getDevice(registrationId);
    }

    public void setDevice(@NonNull String registrationId, @NonNull DeviceSetParam param) {
        deviceClient.setDevice(registrationId, param);
    }

    public AliasGetResult getAlias(@NonNull String alias) {
        return getAlias(alias, null);
    }

    public AliasGetResult getAlias(@NonNull String alias, Platform platform) {
        return deviceClient.getAlias(alias, platform);
    }

    public void deleteAlias(@NonNull String alias) {
        deleteAlias(alias, null);
    }

    public void deleteAlias(@NonNull String alias, Platform platform) {
        deviceClient.deleteAlias(alias, platform);
    }

    public void deleteAliases(@NonNull String alias, @NonNull AliasDeleteParam param) {
        deviceClient.deleteAliases(alias, param);
    }

    public TagsGetResult getTags() {
        return deviceClient.getTags();
    }

    public TagGetResult getTag(@NonNull String tag, @NonNull String registrationId) {
        return deviceClient.getTag(tag, registrationId);
    }

    public void setTag(@NonNull String tag, @NonNull TagSetParam param) {
        deviceClient.setTag(tag, param);
    }

    public void deleteTag(@NonNull String tag) {
        deleteTag(tag, null);
    }

    public void deleteTag(@NonNull String tag, Platform platform) {
        deviceClient.deleteTag(tag, platform);
    }

    public DeviceStatusGetResult getDeviceStatus() {
        return deviceClient.getDeviceStatus();
    }

    public static class Builder {

        private String host = "https://device.jpush.cn";
        private Proxy proxy;
        private String appKey;
        private String masterSecret;
        private Logger.Level loggerLevel = Logger.Level.BASIC;

        public Builder setHost(@NonNull String host) {
            this.host = host;
            return this;
        }

        public Builder setProxy(@NonNull Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder setAppKey(@NonNull String appKey) {
            this.appKey = appKey;
            return this;
        }

        public Builder setMasterSecret(@NonNull String masterSecret) {
            this.masterSecret = masterSecret;
            return this;
        }

        public Builder setLoggerLevel(@NonNull Logger.Level loggerLevel) {
            this.loggerLevel = loggerLevel;
            return this;
        }

        public DeviceApi build() {
            okhttp3.OkHttpClient.Builder delegateBuilder = new okhttp3.OkHttpClient().newBuilder();
            if (proxy != null) {
                delegateBuilder.proxy(proxy);
            }
            DeviceClient deviceClient = Feign.builder()
                    .client(new OkHttpClient(delegateBuilder.build()))
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
                    .encoder(new ApiEncoder())
                    .decoder(new ApiDecoder())
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel)
                    .target(DeviceClient.class, host);
            return new DeviceApi(deviceClient);
        }
    }

}
