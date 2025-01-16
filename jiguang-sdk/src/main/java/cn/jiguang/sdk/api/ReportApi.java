package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.report.*;
import cn.jiguang.sdk.client.ReportClient;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReportApi {

    private final ReportClient reportClient;

    protected ReportApi(@NonNull ReportClient reportClient) {
        this.reportClient = reportClient;
    }

    public List<ReceivedDetailGetResult> getReceivedDetail(ReceivedDetailGetParam param) {
        return reportClient.getReceivedDetail(String.join(",", param.getMsgIds()));
    }

    public Map<String, MessageStatusGetResult> getMessageStatus(MessageStatusGetParam param) {
        return reportClient.getMessageStatus(param);
    }

    public List<MessageDetailGetResult> getMessageDetail(MessageDetailGetParam param) {
        return reportClient.getMessageDetail(String.join(",", param.getMsgIds()));
    }

    public UserDetailGetResult getUserDetail(LocalDate startDate, int duration) {
        return reportClient.getUserDetail(startDate, duration);
    }

    public static class Builder {

        private String host;
        private Client client = new OkHttpClient();
        private String appKey;
        private String masterSecret;
        private Logger.Level loggerLevel = Logger.Level.BASIC;

        public Builder setClient(@NonNull Client client) {
            this.client = client;
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

        public ReportApi build() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ReportClient reportClient = Feign.builder()
                    .client(client)
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel)
                    .target(ReportClient.class, host);
            return new ReportApi(reportClient);
        }
    }

}
