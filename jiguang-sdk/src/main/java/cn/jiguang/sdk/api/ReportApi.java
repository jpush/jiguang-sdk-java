package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.report.*;
import cn.jiguang.sdk.client.ReportClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import cn.jiguang.sdk.exception.ApiErrorException;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
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

    public List<ReceivedDetailGetResult> getReceivedDetail(@NonNull List<String> msgIds) throws ApiErrorException {
        return reportClient.getReceivedDetail(String.join(",", msgIds));
    }

    public Map<String, MessageStatusGetResult> getMessageStatus(@NonNull MessageStatusGetParam param) throws ApiErrorException {
        return reportClient.getMessageStatus(param);
    }

    public List<MessageDetailGetResult> getMessageDetail(@NonNull List<String> msgIds) throws ApiErrorException {
        return reportClient.getMessageDetail(String.join(",", msgIds));
    }

    public UserDetailGetResult getUserDetail(@NonNull LocalDate startDate, @NonNull int duration) throws ApiErrorException {
        return reportClient.getUserDetail(startDate, duration);
    }

    public static class Builder {

        private Client client;
        private Request.Options options;
        private Retryer retryer;
        private String host = "https://report.jpush.cn";
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

        public ReportApi build() {
            Feign.Builder builder = Feign.builder()
                    .client(client != null ? client : new OkHttpClient())
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
                    .encoder(new ApiEncoder())
                    .decoder(new ApiDecoder())
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel);
            if (options != null) {
                builder.options(options);
            }
            if (retryer != null) {
                builder.retryer(retryer);
            }
            return new ReportApi(builder.target(ReportClient.class, host));
        }
    }

}
