package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.push.GroupPushSendParam;
import cn.jiguang.sdk.bean.push.GroupPushSendResult;
import cn.jiguang.sdk.client.GroupPushClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

import java.net.Proxy;

public class GroupPushApi {

    private final GroupPushClient groupPushClient;

    protected GroupPushApi(@NonNull GroupPushClient groupPushClient) {
        this.groupPushClient = groupPushClient;
    }

    public GroupPushSendResult send(@NonNull GroupPushSendParam param) {
        return groupPushClient.send(param);
    }

    public static class Builder {

        private String host = "https://api.jpush.cn";
        private Proxy proxy;
        private String groupKey;
        private String groupMasterSecret;
        private Logger.Level loggerLevel = Logger.Level.BASIC;

        public Builder setHost(@NonNull String host) {
            this.host = host;
            return this;
        }

        public Builder setProxy(@NonNull Proxy proxy) {
            this.proxy = proxy;
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
            okhttp3.OkHttpClient.Builder delegateBuilder = new okhttp3.OkHttpClient().newBuilder();
            if (proxy != null) {
                delegateBuilder.proxy(proxy);
            }
            GroupPushClient groupPushClient = Feign.builder()
                    .client(new OkHttpClient(delegateBuilder.build()))
                    .requestInterceptor(new BasicAuthRequestInterceptor("group-" + groupKey, groupMasterSecret))
                    .encoder(new ApiEncoder())
                    .decoder(new ApiDecoder())
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel)
                    .target(GroupPushClient.class, host);
            return new GroupPushApi(groupPushClient);
        }
    }

}
