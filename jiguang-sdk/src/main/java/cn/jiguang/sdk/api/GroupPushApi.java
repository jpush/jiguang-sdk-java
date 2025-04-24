package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.push.GroupPushSendParam;
import cn.jiguang.sdk.bean.push.GroupPushSendResult;
import cn.jiguang.sdk.client.GroupPushClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

public class GroupPushApi {

    private final GroupPushClient groupPushClient;

    protected GroupPushApi(@NonNull GroupPushClient groupPushClient) {
        this.groupPushClient = groupPushClient;
    }

    public GroupPushSendResult send(@NonNull GroupPushSendParam param) {
        return groupPushClient.send(param);
    }

    public static class Builder {

        private Client client;
        private Request.Options options;
        private Retryer retryer;
        private String host = "https://api.jpush.cn";
        private String groupKey;
        private String groupMasterSecret;
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

        public Builder setGroupKey(@NonNull String groupKey) {
            this.groupKey = groupKey;
            return this;
        }

        public Builder setGroupMasterSecret(@NonNull String groupMasterSecret) {
            this.groupMasterSecret = groupMasterSecret;
            return this;
        }

        public Builder setLoggerLevel(@NonNull Logger.Level loggerLevel) {
            this.loggerLevel = loggerLevel;
            return this;
        }

        public GroupPushApi build() {
            Feign.Builder builder = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor("group-" + groupKey, groupMasterSecret))
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
            return new GroupPushApi(builder.target(GroupPushClient.class, host));
        }
    }

}
