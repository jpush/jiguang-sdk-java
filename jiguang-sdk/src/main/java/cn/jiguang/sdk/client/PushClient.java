package cn.jiguang.sdk.client;

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
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * (<a href="https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push">REST API - Push</a>)
 */
public interface PushClient {

    @RequestLine("POST /v3/push")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushSendResult send(PushSendParam param);

    @RequestLine("POST /v3/push")
    @Headers({"Content-Type: application/json; charset=utf-8", "X-Encrypt-Type: SM2"})
    PushSendResult sendWithSM2(PushSendParam param);

    @RequestLine("POST /v3/schedules")
    @Headers("Content-Type: application/json; charset=utf-8")
    SchedulePushSendResult scheduleSend(SchedulePushSendParam param);

    @RequestLine("GET /v3/push/cid?type=push&count={count}")
    @Headers("Content-Type: application/json; charset=utf-8")
    CidGetResult getCidForPush(@Param("count") int count);

    @RequestLine("GET /v3/push/cid?type=schedule&count={count}")
    @Headers("Content-Type: application/json; charset=utf-8")
    CidGetResult getCidForSchedulePush(@Param("count") int count);

    @RequestLine("POST /v3/push/validate")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushSendResult validateSend(PushSendParam param);

    @RequestLine("DELETE /v3/push/{msg_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void withdrawMessage(@Param("msg_id") String messageId);

    @RequestLine("GET /v3/push/quota")
    @Headers("Content-Type: application/json; charset=utf-8")
    QuotaGetResult getQuota();

    @RequestLine("POST /v3/push/file")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushSendResult sendByFile(PushSendParam param);

    @RequestLine("POST /v3/files/alias")
    @Headers("Content-Type: multipart/form-data")
    FileUploadResult uploadFileForAlias(FileUploadParam param);

    @RequestLine("POST /v3/files/registration_id")
    @Headers("Content-Type: multipart/form-data")
    FileUploadResult uploadFileForRegistrationId(FileUploadParam param);

    @RequestLine("GET /v3/files")
    @Headers("Content-Type: application/json; charset=utf-8")
    FilesGetResult getFiles();

    @RequestLine("GET /v3/files/{file_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    FileGetResult getFile(@Param("file_id") String fileId);

    @RequestLine("DELETE /v3/files/{file_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void deleteFile(@Param("file_id") String fileId);

    @RequestLine("POST /v3/images/byurls")
    @Headers("Content-Type: application/json; charset=utf-8")
    ImageUrlAddResult addImageUrl(ImageUrlAddParam param);

    @RequestLine("PUT /v3/images/byurls/{media_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    ImageUrlUpdateResult updateImageUrl(@Param("media_id") String mediaId, ImageUrlUpdateParam param);

    @RequestLine("POST /v3/images/byfiles")
    @Headers("Content-Type: multipart/form-data")
    ImageFileAddResult addImageFile(ImageFileAddParam param);

    @RequestLine("PUT /v3/images/byfiles/{media_id}")
    @Headers("Content-Type: multipart/form-data")
    ImageFileUpdateResult updateImageFile(@Param("media_id") String mediaId, ImageFileUpdateParam param);

    @RequestLine("POST /v3/push/batch/regid/single")
    @Headers("Content-Type: application/json; charset=utf-8")
    BatchPushSendResult batchSendByRegistrationId(BatchPushSendParam param);

    @RequestLine("POST /v3/push/batch/alias/single")
    @Headers("Content-Type: application/json; charset=utf-8")
    BatchPushSendResult batchSendByAlias(BatchPushSendParam param);

    @RequestLine("POST /v3/push/template")
    @Headers("Content-Type: application/json; charset=utf-8")
    TemplatePushSendResult templateSend(TemplatePushSendParam param);

    @RequestLine("POST /v3/push/template/schedule")
    @Headers("Content-Type: application/json; charset=utf-8")
    ScheduleTemplatePushSendResult scheduleTemplateSend(ScheduleTemplatePushSendParam param);

    // ********************* 如果遇到此api没有及时补充字段的情况，可以自行构建json，调用下面的接口 *********************

    @RequestLine("POST /v3/push")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushSendResult send(Object param);

    @RequestLine("POST /v3/schedules")
    @Headers("Content-Type: application/json; charset=utf-8")
    SchedulePushSendResult scheduleSend(Object param);

    @RequestLine("POST /v3/push/validate")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushSendResult validateSend(Object param);

    @RequestLine("POST /v3/push/file")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushSendResult sendByFile(Object param);

}
