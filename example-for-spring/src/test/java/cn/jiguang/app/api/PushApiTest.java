package cn.jiguang.app.api;

import cn.jiguang.sdk.api.PushApi;
import cn.jiguang.sdk.bean.file.FileGetResult;
import cn.jiguang.sdk.bean.file.FileUploadParam;
import cn.jiguang.sdk.bean.file.FileUploadResult;
import cn.jiguang.sdk.bean.file.FilesGetResult;
import cn.jiguang.sdk.bean.image.*;
import cn.jiguang.sdk.bean.push.PushSendParam;
import cn.jiguang.sdk.bean.push.PushSendResult;
import cn.jiguang.sdk.bean.push.audience.Audience;
import cn.jiguang.sdk.bean.push.message.notification.NotificationMessage;
import cn.jiguang.sdk.bean.push.other.CidGetResult;
import cn.jiguang.sdk.bean.push.other.QuotaGetResult;
import cn.jiguang.sdk.constants.ApiConstants;
import cn.jiguang.sdk.enums.platform.Platform;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Arrays;

@Slf4j
@SpringBootTest()
@RunWith(SpringRunner.class)
public class PushApiTest {

    @Autowired
    private PushApi pushApi;

    @Test
    public void getCidForPush() {
        CidGetResult result = pushApi.getCidForPush(10);
        log.info("result:{}", result);
    }

    @Test
    public void send() {
        PushSendParam param = new PushSendParam();
        // 通知内容
        NotificationMessage.Android android = new NotificationMessage.Android();
        android.setAlert("this is android alert");
        android.setTitle("this is android title");
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setAlert("this is alert");
        notificationMessage.setAndroid(android);
        param.setNotification(notificationMessage);

        // 目标人群
        Audience audience = new Audience();
        audience.setRegistrationIdList(Arrays.asList("1104a89793af2cfc030", "1104a89793af2cfc030"));
        // 指定目标
        param.setAudience(audience);
        // 或者发送所有人
        // param.setAudience(ApiConstants.Audience.ALL);

        // 指定平台
        param.setPlatform(Arrays.asList(Platform.android, Platform.ios));
        // 或者发送所有平台
        // param.setPlatform(ApiConstants.Platform.ALL);

        // 发送
        PushSendResult result = pushApi.send(param);
        log.info("result:{}", result);
    }

    @Test
    public void sendByFile() {
        PushSendParam param = new PushSendParam();
        // 通知内容
        NotificationMessage.Android android = new NotificationMessage.Android();
        android.setAlert("this is android alert");
        android.setTitle("this is android title");
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setAlert("this is alert");
        notificationMessage.setAndroid(android);
        param.setNotification(notificationMessage);

        // 目标人群
        Audience.File file = new Audience.File();
        file.setFileId("b266cd5c8544ba09b23733e3-bb3f823c-32c4-4153-866b-0471031ce4ba");
        Audience audience = new Audience();
        audience.setFile(file);
        // 指定目标
        param.setAudience(audience);

        // 发送所有平台
        param.setPlatform(ApiConstants.Platform.ALL);

        // 发送
        PushSendResult result = pushApi.sendByFile(param);
        log.info("result:{}", result);
    }

    @Test
    public void validateSend() {
        PushSendParam param = new PushSendParam();
        // 通知内容
        NotificationMessage.Android android = new NotificationMessage.Android();
        android.setAlert("this is android alert");
        android.setTitle("this is android title");
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setAlert("this is alert");
        notificationMessage.setAndroid(android);
        param.setNotification(notificationMessage);

        // 目标人群
        Audience audience = new Audience();
        audience.setRegistrationIdList(Arrays.asList("1104a89793af2cfc030", "1104a89793af2cfc030"));
        // 指定目标
        param.setAudience(audience);
        // 或者发送所有人
        // param.setAudience(ApiConstants.Audience.ALL);

        // 指定平台
        param.setPlatform(Arrays.asList(Platform.android, Platform.ios));
        // 或者发送所有平台
        // param.setPlatform(ApiConstants.Platform.ALL);

        PushSendResult result = pushApi.validateSend(param);
        log.info("result:{}", result);
    }

    @Test
    public void withdrawMessage() {
        String messageId = "18101213529672826";
        pushApi.withdrawMessage(messageId);
    }

    @Test
    public void getQuota() {
        QuotaGetResult result = pushApi.getQuota();
        log.info("result:{}", result);
    }

    @Test
    public void uploadFileForAlias() {
        FileUploadParam param = new FileUploadParam();
        param.setFile(new File("/Users/z/Desktop/alias.txt"));
        param.setTtl(720);
        FileUploadResult result = pushApi.uploadFileForAlias(param);
        log.info("result:{}", result);
    }

    @Test
    public void uploadFileForRegistrationId() {
        FileUploadParam param = new FileUploadParam();
        param.setFile(new File("/Users/z/Desktop/registrationId.txt"));
        param.setTtl(720);
        FileUploadResult result = pushApi.uploadFileForRegistrationId(param);
        log.info("result:{}", result);
    }

    @Test
    public void getFiles() {
        FilesGetResult result = pushApi.getFiles();
        log.info("result:{}", result);
    }

    @Test
    public void getFile() {
        String fileId = "b266cd5c8544ba09b23733e3-6f82b892-206a-4b91-a8f4-1aaa4471c918";
        FileGetResult result = pushApi.getFile(fileId);
        log.info("result:{}", result);
    }

    @Test
    public void deleteFile() {
        String fileId = "b266cd5c8544ba09b23733e3-6f82b892-206a-4b91-a8f4-1aaa4471c918";
        pushApi.deleteFile(fileId);
    }

    @Test
    public void addImageUrl() {
        ImageUrlAddParam param = new ImageUrlAddParam();
        param.setImageType(1);
        param.setJiguangImageUrl("https://img.jiguang.cn/jiguang/public/img/f237811.png");
        ImageUrlAddResult result = pushApi.addImageUrl(param);
        log.info("result:{}", result);
    }

    @Test
    public void updateImageUrl() {
        String mediaId = "jgmedia-1-c9f54726-6a06-4ed1-842d-81e32383ee5c";
        ImageUrlUpdateParam param = new ImageUrlUpdateParam();
        param.setJiguangImageUrl("https://img.jiguang.cn/jiguang/public/img/c866bd2.png");
        ImageUrlUpdateResult result = pushApi.updateImageUrl(mediaId, param);
        log.info("result:{}", result);
    }

    @Test
    public void addImageFile() {
        ImageFileAddParam param = new ImageFileAddParam();
        param.setImageType(1);
        param.setOppoImageFile(new File("/Users/z/Desktop/官网封面.jpg"));
        ImageFileAddResult result = pushApi.addImageFile(param);
        log.info("result:{}", result);
    }

    @Test
    public void updateImageFile() {
        String mediaId = "jgmedia-1-c20d4b1f-e821-430d-b651-91c0c4bf1f60";
        ImageFileUpdateParam param = new ImageFileUpdateParam();
        param.setOppoImageFile(new File("/Users/z/Desktop/官网封面.jpg"));
        ImageFileUpdateResult result = pushApi.updateImageFile(mediaId, param);
        log.info("result:{}", result);
    }

}
