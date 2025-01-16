package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.geofence.*;
import cn.jiguang.sdk.client.GeofenceClient;
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

public class GeofenceApi {

    private final GeofenceClient geofenceClient;

    protected GeofenceApi(@NonNull GeofenceClient geofenceClient) {
        this.geofenceClient = geofenceClient;
    }

    public GeofenceCreateResult create(GeofenceCreateParam param) {
        return geofenceClient.create(param);
    }

    public void update(String geofenceId, GeofenceCreateParam param) {
        geofenceClient.update(geofenceId, param);
    }

    public void delete(String geofenceId) {
        geofenceClient.delete(geofenceId);
    }

    public GeofenceGetResult get(GeofenceGetParam param) {
        return geofenceClient.get(param.getGeofenceId());
    }

    public GeofencePageResult page(GeofencePageParam param) {
        return geofenceClient.page(param.getPage(), param.getSize(), param.getName());
    }

    public GeofenceGetOutlineResult getOutlineResult() {
        return geofenceClient.getOutlines();
    }

    public static class Builder {
        private Client client = new OkHttpClient();
        private String host;
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

        public GeofenceApi build() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            GeofenceClient pushClient = Feign.builder()
                    .client(client)
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
                    .errorDecoder(new ApiErrorDecoder())
                    .logger(new Slf4jLogger())
                    .logLevel(loggerLevel)
                    .target(GeofenceClient.class, host);
            return new GeofenceApi(pushClient);
        }
    }

}
