package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.schedule.*;
import cn.jiguang.sdk.client.ScheduleClient;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

import java.util.List;

public class ScheduleApi {

    private final ScheduleClient scheduleClient;

    protected ScheduleApi(@NonNull ScheduleClient scheduleClient) {
        this.scheduleClient = scheduleClient;
    }

    public SchedulePushResult schedulePush(SchedulePushParam param) {
        return scheduleClient.schedulePush(param);
    }

    public void updateSchedulePush(String scheduleId, SchedulePushParam param) {
        scheduleClient.updateSchedulePush(scheduleId, param);
    }

    public void deleteSchedulePush(String scheduleId) {
        scheduleClient.deleteSchedulePush(scheduleId);
    }

    public SchedulePushPageResult listSchedulePush(SchedulePushPageParam param) {
        return scheduleClient.listSchedulePush(param.getPage());
    }

    public List<SchedulePushParam> getSchedulePush(String scheduleId) {
        return scheduleClient.getSchedulePush(scheduleId);
    }

    public SchedulePushDetailGetResult getSchedulePushDetail(SchedulePushDetailGetParam param) {
        return scheduleClient.getSchedulePushDetail(param.getScheduleId());
    }

    public static class Builder {

        private Client client = new OkHttpClient();
        private String host;
        private String appKey;
        private String masterSecret;
        private Logger.Level loggerLevel = Logger.Level.BASIC;

        public Builder setHost(@NonNull String host) {
            this.host = host;
            return this;
        }

        public Builder setClient(@NonNull Client client) {
            this.client = client;
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

        public ScheduleApi build() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ScheduleClient scheduleClient = Feign.builder()
                    .client(client)
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel)
                    .target(ScheduleClient.class, host);
            return new ScheduleApi(scheduleClient);
        }
    }

}
