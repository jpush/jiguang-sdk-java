package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.device.*;
import cn.jiguang.sdk.client.DeviceClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import cn.jiguang.sdk.enums.platform.Platform;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

import java.util.List;

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

    public void clearDevice(@NonNull String registrationId, @NonNull DeviceClearParam param) {
        deviceClient.clearDevice(registrationId, param);
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

    public DeviceStatusGetResult getDeviceStatus(List<String> registrationIds) {
        return deviceClient.getDeviceStatus(registrationIds);
    }

    public static class Builder {

        private Client client;
        private Request.Options options;
        private Retryer retryer;
        private String host = "https://device.jpush.cn";
        private String appKey;
        private String masterSecret;
        private Logger.Level loggerLevel = Logger.Level.BASIC;

        public Builder setClient(@NonNull Client client) {
            this.client = client;
            return this;
        }

        public Builder setOptions(@NonNull Request.Options options) {
            this.options = options;
            return this;
        }

        public Builder setRetryer(@NonNull Retryer retryer) {
            this.retryer = retryer;
            return this;
        }

        public Builder setHost(@NonNull String host) {
            this.host = host;
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
            Feign.Builder builder = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
                    .encoder(new ApiEncoder())
                    .decoder(new ApiDecoder())
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel);
            if (client != null) {
                builder.client(client);
            }
            if (options != null) {
                builder.options(options);
            }
            if (retryer != null) {
                builder.retryer(retryer);
            }
            return new DeviceApi(builder.target(DeviceClient.class, host));
        }
    }

}
