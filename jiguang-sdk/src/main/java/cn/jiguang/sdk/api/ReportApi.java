package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.report.*;
import cn.jiguang.sdk.client.ReportClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
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

    public List<ReceivedDetailGetResult> getReceivedDetail(@NonNull List<String> msgIds) {
        return reportClient.getReceivedDetail(String.join(",", msgIds));
    }

    public Map<String, MessageStatusGetResult> getMessageStatus(@NonNull MessageStatusGetParam param) {
        return reportClient.getMessageStatus(param);
    }

    public List<MessageDetailGetResult> getMessageDetail(@NonNull List<String> msgIds) {
        return reportClient.getMessageDetail(String.join(",", msgIds));
    }

    public UserDetailGetResult getUserDetail(@NonNull LocalDate startDate, @NonNull int duration) {
        return reportClient.getUserDetail(startDate, duration);
    }

    public static class Builder {

        private String host = "https://report.jpush.cn";
        private String appKey;
        private String masterSecret;
        private Logger.Level loggerLevel = Logger.Level.BASIC;

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
            ReportClient reportClient = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
                    .encoder(new ApiEncoder())
                    .decoder(new ApiDecoder())
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel)
                    .target(ReportClient.class, host);
            return new ReportApi(reportClient);
        }
    }

}
