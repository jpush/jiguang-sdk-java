package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.admin.*;
import cn.jiguang.sdk.client.AdminClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import feign.*;
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

        private Client client;
        private Request.Options options;
        private Retryer retryer;
        private String host = "https://admin.jpush.cn";
        private String devKey;
        private String devSecret;
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
            Feign.Builder builder = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor(devKey, devSecret))
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
            return new AdminApi(builder.target(AdminClient.class, host));
        }
    }

}
