package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.admin.*;
import cn.jiguang.sdk.client.AdminClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

public class AdminApi {

    private final AdminClient adminClient;

    protected AdminApi(@NonNull AdminClient adminClient) {
        this.adminClient = adminClient;
    }

    public AppCreateResult createApp(@NonNull AppCreateParam param) {
        return adminClient.createApp(param);
    }

    public AppDeleteResult deleteApp(@NonNull String appKey) {
        return adminClient.deleteApp(appKey);
    }

    public CertificateUploadResult uploadCertificate(@NonNull String appKey, @NonNull CertificateUploadParam param) {
        return adminClient.uploadCertificate(appKey, param);
    }

    public static class Builder {

        private String host = "https://admin.jpush.cn";
        private String devKey;
        private String devSecret;
        private Logger.Level loggerLevel = Logger.Level.BASIC;

        public Builder setHost(@NonNull String host) {
            this.host = host;
            return this;
        }

        public Builder setDevKey(@NonNull String devKey) {
            this.devKey = devKey;
            return this;
        }

        public Builder setDevSecret(@NonNull String devSecret) {
            this.devSecret = devSecret;
            return this;
        }

        public Builder setLoggerLevel(@NonNull Logger.Level loggerLevel) {
            this.loggerLevel = loggerLevel;
            return this;
        }

        public AdminApi build() {
            AdminClient adminClient = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor(devKey, devSecret))
                    .encoder(new ApiEncoder())
                    .decoder(new ApiDecoder())
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel)
                    .target(AdminClient.class, host);
            return new AdminApi(adminClient);
        }
    }

}
