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
import cn.jiguang.sdk.exception.ApiErrorException;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.NonNull;

public class PushApi {

    private final PushClient pushClient;

    protected PushApi(@NonNull PushClient pushClient) {
        this.pushClient = pushClient;
    }

    public PushSendResult send(@NonNull PushSendParam param) throws ApiErrorException {
        return pushClient.send(param);
    }

    public PushSendResult sendWithSM2(@NonNull PushSendParam param) throws ApiErrorException {
        return pushClient.sendWithSM2(param);
    }

    public SchedulePushSendResult scheduleSend(@NonNull SchedulePushSendParam param) throws ApiErrorException {
        return pushClient.scheduleSend(param);
    }

    public CidGetResult getCidForPush(@NonNull int count) throws ApiErrorException {
        return pushClient.getCidForPush(count);
    }

    public CidGetResult getCidForSchedulePush(@NonNull int count) throws ApiErrorException {
        return pushClient.getCidForSchedulePush(count);
    }

    public PushSendResult validateSend(@NonNull PushSendParam param) throws ApiErrorException {
        return pushClient.validateSend(param);
    }

    public void withdrawMessage(@NonNull String messageId) throws ApiErrorException {
        pushClient.withdrawMessage(messageId);
    }

    public QuotaGetResult getQuota() throws ApiErrorException {
        return pushClient.getQuota();
    }

    public PushSendResult sendByFile(@NonNull PushSendParam param) throws ApiErrorException {
        return pushClient.sendByFile(param);
    }

    public FileUploadResult uploadFileForAlias(@NonNull FileUploadParam param) throws ApiErrorException {
        return pushClient.uploadFileForAlias(param);
    }

    public FileUploadResult uploadFileForRegistrationId(@NonNull FileUploadParam param) throws ApiErrorException {
        return pushClient.uploadFileForRegistrationId(param);
    }

    public FilesGetResult getFiles() throws ApiErrorException {
        return pushClient.getFiles();
    }

    public FileGetResult getFile(@NonNull String fileId) throws ApiErrorException {
        return pushClient.getFile(fileId);
    }

    public void deleteFile(@NonNull String fileId) throws ApiErrorException {
        pushClient.deleteFile(fileId);
    }

    public ImageUrlAddResult addImageUrl(@NonNull ImageUrlAddParam param) throws ApiErrorException {
        return pushClient.addImageUrl(param);
    }

    public ImageUrlUpdateResult updateImageUrl(@NonNull String mediaId, @NonNull ImageUrlUpdateParam param) throws ApiErrorException {
        return pushClient.updateImageUrl(mediaId, param);
    }

    public ImageFileAddResult addImageFile(@NonNull ImageFileAddParam param) throws ApiErrorException {
        return pushClient.addImageFile(param);
    }

    public ImageFileUpdateResult updateImageFile(@NonNull String mediaId, @NonNull ImageFileUpdateParam param) throws ApiErrorException {
        return pushClient.updateImageFile(mediaId, param);
    }

    public BatchPushSendResult batchSendByRegistrationId(BatchPushSendParam param) throws ApiErrorException {
        return pushClient.batchSendByRegistrationId(param);
    }

    public BatchPushSendResult batchSendByAlias(BatchPushSendParam param) throws ApiErrorException {
        return pushClient.batchSendByAlias(param);
    }

    public TemplatePushSendResult templateSend(TemplatePushSendParam param) throws ApiErrorException {
        return pushClient.templateSend(param);
    }

    public ScheduleTemplatePushSendResult scheduleTemplateSend(ScheduleTemplatePushSendParam param) throws ApiErrorException {
        return pushClient.scheduleTemplateSend(param);
    }

    // ********************* 如果遇到此api没有及时补充字段的情况，可以自行构建json，调用下面的接口 *********************

    public PushSendResult send(Object param) throws ApiErrorException {
        return pushClient.send(param);
    }

    public SchedulePushSendResult scheduleSend(Object param) throws ApiErrorException {
        return pushClient.scheduleSend(param);
    }

    public PushSendResult validateSend(Object param) throws ApiErrorException {
        return pushClient.validateSend(param);
    }

    public PushSendResult sendByFile(Object param) throws ApiErrorException {
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
            return new PushApi(builder.target(PushClient.class, host));
        }
    }

}
