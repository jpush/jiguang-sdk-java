package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.file.FileGetResult;
import cn.jiguang.sdk.bean.file.FileUploadParam;
import cn.jiguang.sdk.bean.file.FileUploadResult;
import cn.jiguang.sdk.bean.file.FilesGetResult;
import cn.jiguang.sdk.bean.image.*;
import cn.jiguang.sdk.bean.push.*;
import cn.jiguang.sdk.bean.push.batch.BatchPushSendParam;
import cn.jiguang.sdk.bean.push.batch.BatchPushSendResult;
import cn.jiguang.sdk.bean.push.other.CidGetResult;
import cn.jiguang.sdk.bean.push.other.QuotaGetResult;
import cn.jiguang.sdk.client.PushClient;
import cn.jiguang.sdk.codec.ApiDecoder;
import cn.jiguang.sdk.codec.ApiEncoder;
import cn.jiguang.sdk.codec.ApiErrorDecoder;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

public class PushApi {

    private final PushClient pushClient;

    protected PushApi(@NonNull PushClient pushClient) {
        this.pushClient = pushClient;
    }

    public PushSendResult send(@NonNull PushSendParam param) {
        return pushClient.send(param);
    }

    public PushSendResult sendWithSM2(@NonNull PushSendParam param) {
        return pushClient.sendWithSM2(param);
    }

    public SchedulePushSendResult scheduleSend(@NonNull SchedulePushSendParam param) {
        return pushClient.scheduleSend(param);
    }

    public CidGetResult getCidForPush(@NonNull int count) {
        return pushClient.getCidForPush(count);
    }

    public CidGetResult getCidForSchedulePush(@NonNull int count) {
        return pushClient.getCidForSchedulePush(count);
    }

    public PushSendResult validateSend(@NonNull PushSendParam param) {
        return pushClient.validateSend(param);
    }

    public void withdrawMessage(@NonNull String messageId) {
        pushClient.withdrawMessage(messageId);
    }

    public QuotaGetResult getQuota() {
        return pushClient.getQuota();
    }

    public PushSendResult sendByFile(@NonNull PushSendParam param) {
        return pushClient.sendByFile(param);
    }

    public FileUploadResult uploadFileForAlias(@NonNull FileUploadParam param) {
        return pushClient.uploadFileForAlias(param);
    }

    public FileUploadResult uploadFileForRegistrationId(@NonNull FileUploadParam param) {
        return pushClient.uploadFileForRegistrationId(param);
    }

    public FilesGetResult getFiles() {
        return pushClient.getFiles();
    }

    public FileGetResult getFile(@NonNull String fileId) {
        return pushClient.getFile(fileId);
    }

    public void deleteFile(@NonNull String fileId) {
        pushClient.deleteFile(fileId);
    }

    public ImageUrlAddResult addImageUrl(@NonNull ImageUrlAddParam param) {
        return pushClient.addImageUrl(param);
    }

    public ImageUrlUpdateResult updateImageUrl(@NonNull String mediaId, @NonNull ImageUrlUpdateParam param) {
        return pushClient.updateImageUrl(mediaId, param);
    }

    public ImageFileAddResult addImageFile(@NonNull ImageFileAddParam param) {
        return pushClient.addImageFile(param);
    }

    public ImageFileUpdateResult updateImageFile(@NonNull String mediaId, @NonNull ImageFileUpdateParam param) {
        return pushClient.updateImageFile(mediaId, param);
    }

    public BatchPushSendResult batchSendByRegistrationId(BatchPushSendParam param) {
        return pushClient.batchSendByRegistrationId(param);
    }

    public BatchPushSendResult batchSendByAlias(BatchPushSendParam param) {
        return pushClient.batchSendByAlias(param);
    }

    public TemplatePushSendResult templateSend(TemplatePushSendParam param) {
        return pushClient.templateSend(param);
    }

    public ScheduleTemplatePushSendResult scheduleTemplateSend(ScheduleTemplatePushSendParam param) {
        return pushClient.scheduleTemplateSend(param);
    }

    // ********************* 如果遇到此api没有及时补充字段的情况，可以自行构建json，调用下面的接口 *********************

    public PushSendResult send(Object param) {
        return pushClient.send(param);
    }

    public SchedulePushSendResult scheduleSend(Object param) {
        return pushClient.scheduleSend(param);
    }

    public PushSendResult validateSend(Object param) {
        return pushClient.validateSend(param);
    }

    public PushSendResult sendByFile(Object param) {
        return pushClient.sendByFile(param);
    }

    public static class Builder {

        private Client client;
        private Request.Options options;
        private Retryer retryer;
        private String host = "https://api.jpush.cn";
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

        public PushApi build() {
            Feign.Builder builder = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor(appKey, masterSecret))
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
            return new PushApi(builder.target(PushClient.class, host));
        }
    }

}
