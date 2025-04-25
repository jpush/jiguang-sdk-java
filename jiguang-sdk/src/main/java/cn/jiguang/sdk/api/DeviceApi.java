package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.device.*;
import cn.jiguang.sdk.client.DeviceClient;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import cn.jiguang.sdk.enums.platform.Platform;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeviceApi {

    private final DeviceClient deviceClient;

    protected DeviceApi(@NonNull DeviceClient deviceClient) {
        this.deviceClient = deviceClient;
    }

    public DeviceGetResult getDevice(String registrationId) {
        return deviceClient.getDevice(registrationId);
    }

    public void setDevice(String registrationId, DeviceSetParam param) {
        deviceClient.setDevice(registrationId, param);
    }

    public AliasGetResult getAlias(AliasGetParam param) {
        String platform = Optional.ofNullable(param.getPlatforms())
                .filter(platforms -> !platforms.isEmpty())
                .map(platforms -> platforms.stream().map(Platform::name).collect(Collectors.joining(",")))
                .orElse(null);
        return deviceClient.getAlias(param.getAlias(), platform);
    }

    public void deleteAlias(AliasDeleteParam param) {
        String platform = Optional.ofNullable(param.getPlatforms())
                .filter(platforms -> !platforms.isEmpty())
                .map(platforms -> platforms.stream().map(Platform::name).collect(Collectors.joining(",")))
                .orElse(null);
        deviceClient.deleteAlias(param.getAlias(), platform);
    }

    public TagsGetResult getTags() {
        return deviceClient.getTags();
    }

    public TagGetResult getTag(TagGetParam param) {
        return deviceClient.getTag(param.getTag(), param.getRegistrationId());
    }

    public void setTag(String tag, TagSetParam param) {
        deviceClient.setTag(tag, param);
    }

    public void deleteTag(TagDeleteParam param) {
        String platform = Optional.ofNullable(param.getPlatforms())
                .filter(platforms -> !platforms.isEmpty())
                .map(platforms -> platforms.stream().map(Platform::name).collect(Collectors.joining(",")))
                .orElse(null);
        deviceClient.deleteTag(param.getTag(), platform);
    }

    public List<DeviceStatusGetResult> getDeviceStatus(List<String> registrationIds) {
        return deviceClient.getDeviceStatus(registrationIds);
    }

    public void setWebDevice(String registrationId, WebDeviceSetParam param) {
        deviceClient.setWebDevice(registrationId, param);
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
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Feign.Builder builder = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
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
