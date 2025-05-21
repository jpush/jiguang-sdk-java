package cn.jiguang.app.api;

import cn.jiguang.sdk.api.PushApi;
import cn.jiguang.sdk.bean.file.FileGetResult;
import cn.jiguang.sdk.bean.file.FileUploadParam;
import cn.jiguang.sdk.bean.file.FileUploadResult;
import cn.jiguang.sdk.bean.file.FilesGetResult;
import cn.jiguang.sdk.bean.image.*;
import cn.jiguang.sdk.bean.push.*;
import cn.jiguang.sdk.bean.push.audience.Audience;
import cn.jiguang.sdk.bean.push.batch.BatchPushParam;
import cn.jiguang.sdk.bean.push.batch.BatchPushSendParam;
import cn.jiguang.sdk.bean.push.batch.BatchPushSendResult;
import cn.jiguang.sdk.bean.push.callback.Callback;
import cn.jiguang.sdk.bean.push.message.custom.CustomMessage;
import cn.jiguang.sdk.bean.push.message.notification.NotificationMessage;
import cn.jiguang.sdk.bean.push.message.notification.ThirdNotificationMessage;
import cn.jiguang.sdk.bean.push.options.Options;
import cn.jiguang.sdk.bean.push.other.CidGetResult;
import cn.jiguang.sdk.bean.push.other.QuotaGetResult;
import cn.jiguang.sdk.bean.push.other.TemplateParam;
import cn.jiguang.sdk.constants.ApiConstants;
import cn.jiguang.sdk.enums.platform.Platform;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@SpringBootTest()
@RunWith(SpringRunner.class)
public class PushApiTest {

    @Autowired
    private PushApi pushApi;

    @Autowired
    private ObjectMapper objectMapper;

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

        NotificationMessage.IOS iOS = new NotificationMessage.IOS();
        Map<String, String> iOSAlert = new HashMap<>();
        iOSAlert.put("title", "this is iOS title");
        iOSAlert.put("subtitle", "this is iOS subtitle");
        iOS.setAlert(iOSAlert);

        Map<String, Object> extrasMap = new HashMap<>();
        Map<String, Object> extrasParamMap = new HashMap<>();
        extrasParamMap.put("key1", "value1");
        extrasParamMap.put("key2", "value2");
        extrasMap.put("params", extrasParamMap);
        android.setExtras(extrasMap);
        iOS.setExtras(extrasMap);

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setAlert("this is alert");
        notificationMessage.setAndroid(android);
        notificationMessage.setIos(iOS);
        param.setNotification(notificationMessage);

        // 目标人群
        Audience audience = new Audience();
        audience.setRegistrationIdList(Arrays.asList("1104a89793af2cfc030", "1104a89793af2cfc030"));
        // 指定目标
        // param.setAudience(audience);

        // 或者发送所有人
        param.setAudience(ApiConstants.Audience.ALL);

        // 指定平台
        param.setPlatform(Arrays.asList(Platform.android, Platform.ios));
        // 或者发送所有平台
        // param.setPlatform(ApiConstants.Platform.ALL);

        // 短信补充
        // param.setSmsMessage();

        // 回调
        // param.setCallback();

        // options
        Options options = new Options();
        Map<String, Object> thirdPartyMap = new HashMap<>();
        Map<String, Object> huaweiMap = new HashMap<>();
        huaweiMap.put("distribution", "first_ospush");
        huaweiMap.put("importance", "NORMAL");
        huaweiMap.put("category", "MARKETING");
        thirdPartyMap.put("huawei", huaweiMap);
        options.setThirdPartyChannel(thirdPartyMap);
        param.setOptions(options);

        Map<String, Object> callbackParams = new HashMap<>();
        callbackParams.put("callbackKey", "callbackValue");
        Callback callback = new Callback();
        callback.setParams(callbackParams);
        param.setCallback(callback);

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

    @Test
    public void batchSendByRegistrationId() {
        NotificationMessage.Android android = new NotificationMessage.Android();
        android.setAlert("this is android alert");
        android.setTitle("this is android title");
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setAndroid(android);

        BatchPushParam pushParam = new BatchPushParam();
        pushParam.setTarget("170976fa8b808c38fe7");
        pushParam.setNotification(notificationMessage);
        pushParam.setPlatform(Arrays.asList(Platform.android, Platform.ios));

        Map<String, BatchPushParam> pushSendParam = new HashMap<>();
        pushSendParam.put("b266cd5c8544ba09b23733e3-c7f656ad-12a3-4807-a614-9924dc11d79e", pushParam);
        pushSendParam.put("b266cd5c8544ba09b23733e3-0cbee7fc-e7ba-4b87-952e-14c5be18e671", pushParam);
        pushSendParam.put("b266cd5c8544ba09b23733e3-870bec54-f0e7-4ae0-a1f9-f13b4ddd1629", pushParam);

        BatchPushSendParam param = new BatchPushSendParam();
        param.setSendParam(pushSendParam);

        BatchPushSendResult result = pushApi.batchSendByRegistrationId(param);
        log.info("result:{}", result);
    }

    @Test
    public void batchSendByAlias() {
        NotificationMessage.Android android = new NotificationMessage.Android();
        android.setAlert("this is android alert");
        android.setTitle("this is android title");
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setAndroid(android);

        BatchPushParam pushParam = new BatchPushParam();
        pushParam.setTarget("alias1");
        pushParam.setNotification(notificationMessage);
        pushParam.setPlatform(Arrays.asList(Platform.android, Platform.ios));

        Map<String, BatchPushParam> pushSendParam = new HashMap<>();
        pushSendParam.put("b266cd5c8544ba09b23733e3-6386dd3c-87dd-42c8-b721-a184ea323371", pushParam);
        pushSendParam.put("b266cd5c8544ba09b23733e3-999c861b-426c-475d-97c8-0cb884e67dfd", pushParam);
        pushSendParam.put("b266cd5c8544ba09b23733e3-d6296fc1-8e7b-4fdd-81ee-019de077bd97", pushParam);

        BatchPushSendParam param = new BatchPushSendParam();
        param.setSendParam(pushSendParam);

        BatchPushSendResult result = pushApi.batchSendByAlias(param);
        log.info("result:{}", result);
    }

    @Test
    public void templateSend() {
        Map<String, String> keys = new HashMap<>();
        keys.put("title", "template-title");
        keys.put("content", "template-content");
        keys.put("deeplink", "intent:#Intent;component=com.jiguang.push/com.example.jpushdemo.SettingActivity;end");

        List<TemplateParam> templateParams = new ArrayList<>();
        TemplateParam templateParam = new TemplateParam();
        templateParam.setKeys(keys);
        templateParam.setAudience(ApiConstants.Audience.ALL);
        templateParams.add(templateParam);

        TemplatePushSendParam param = new ScheduleTemplatePushSendParam();
        param.setId("1770D8D90FA49AAA");
        param.setParams(templateParams);
        TemplatePushSendResult result = pushApi.templateSend(param);
        log.info("result:{}", result);
    }

    @Test
    public void scheduleTemplateSend() {
        Map<String, String> keys = new HashMap<>();
        keys.put("xxx", "王");
        keys.put("number", "6666");
        keys.put("amount", "9999");

        List<TemplateParam> templateParams = new ArrayList<>();
        TemplateParam templateParam = new TemplateParam();
        templateParam.setKeys(keys);
        templateParam.setAudience(ApiConstants.Audience.ALL);
        templateParams.add(templateParam);

        SchedulePushSendParam.Trigger.Single single = new SchedulePushSendParam.Trigger.Single();
        single.setTime(LocalDateTime.now().plusDays(1));
        SchedulePushSendParam.Trigger trigger = new SchedulePushSendParam.Trigger();
        trigger.setSingle(single);

        ScheduleTemplatePushSendParam param = new ScheduleTemplatePushSendParam();
        param.setId("1770D8D90FA4994D");
        param.setParams(templateParams);
        param.setScheduleName("定时模板推送示例");
        param.setTrigger(trigger);
        ScheduleTemplatePushSendResult result = pushApi.scheduleTemplateSend(param);
        log.info("result:{}", result);
    }

    @Test
    public void custom2Notification3rd() {
        PushSendParam param = new PushSendParam();
        // 指定目标
        Audience audience = new Audience();
        audience.setRegistrationIdList(Arrays.asList("171976fa8bbde8d9ab6"));
        param.setAudience(audience);
        // 或者发送所有人
        // param.setAudience(ApiConstants.Audience.ALL);

        // 指定平台
        param.setPlatform(Arrays.asList(Platform.android, Platform.ios));
        // 或者发送所有平台
        // param.setPlatform(ApiConstants.Platform.ALL);

        // 自定义消息转厂商通知，必须指定版本为"v2"，否则报错
        Options options = new Options();
        options.setNotification3rdVer("v2");
        param.setOptions(options);

        // 自定义消息转厂商通知，其中的自定义消息
        CustomMessage customMessage = new CustomMessage();
        customMessage.setContent("this is custom message content");
        param.setCustom(customMessage);

        // 自定义消息转厂商通知，其中的厂商通知
        NotificationMessage.Android android = new NotificationMessage.Android();
        android.setAlert("this is android alert");
        android.setTitle("this is android title");

        NotificationMessage.IOS iOS = new NotificationMessage.IOS();
        Map<String, String> iOSAlert = new HashMap<>();
        iOSAlert.put("title", "this is iOS title");
        iOSAlert.put("subtitle", "this is iOS subtitle");
        iOS.setAlert(iOSAlert);

        NotificationMessage.HMOS hmos = new NotificationMessage.HMOS();
        hmos.setAlert("this is hmos alert");

        ThirdNotificationMessage thirdNotificationMessage = new ThirdNotificationMessage();
        thirdNotificationMessage.setAndroid(android);
        thirdNotificationMessage.setIos(iOS);
        thirdNotificationMessage.setHmos(hmos);
        param.setThirdNotificationMessage(thirdNotificationMessage);

        // 发送
        PushSendResult result = pushApi.send(param);
        log.info("result:{}", result);
    }

}
