package cn.jiguang.app.api;

import cn.jiguang.sdk.api.PushApi;
import cn.jiguang.sdk.bean.push.PushParam;
import cn.jiguang.sdk.bean.push.PushResult;
import cn.jiguang.sdk.bean.push.other.CidGetParam;
import cn.jiguang.sdk.bean.schedule.SchedulePushParam;
import cn.jiguang.sdk.bean.schedule.SchedulePushResult;
import cn.jiguang.sdk.bean.push.audience.Audience;
import cn.jiguang.sdk.bean.push.message.notification.NotificationMessage;
import cn.jiguang.sdk.bean.push.options.Options;
import cn.jiguang.sdk.bean.push.other.CidGetResult;
import cn.jiguang.sdk.bean.push.other.QuotaGetResult;
import cn.jiguang.sdk.constants.ApiConstants;
import cn.jiguang.sdk.enums.platform.Platform;
import cn.jiguang.sdk.enums.timeunit.TimeUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PushApiTest {

    @Autowired
    private PushApi pushApi;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCidForPush() {
        CidGetParam param = new CidGetParam();
        param.setCount(10);
        CidGetResult result = pushApi.getCidForPush(param);
        log.info("result:{}", result);
    }

    @Test
    public void push() {
        PushParam param = new PushParam();
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

        NotificationMessage.Web web = new NotificationMessage.Web();
        web.setAlert("this is web alert");
        web.setTitle("this is web title");

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setAlert("this is alert");
        notificationMessage.setAndroid(android);
        notificationMessage.setIos(iOS);
        notificationMessage.setWeb(web);
        param.setNotification(notificationMessage);

        // 目标人群
        Audience audience = new Audience();
        audience.setRegistrationIdList(Arrays.asList("193e35f7e057954af4b"));
        // 指定目标
        param.setAudience(audience);

        // 或者发送所有人
        // param.setAudience(ApiConstants.Audience.ALL);

        // 指定平台
        param.setPlatform(Arrays.asList(Platform.android, Platform.ios, Platform.web));
        // 或者发送所有平台
        // param.setPlatform(ApiConstants.Platform.ALL);

        // Android厂商
        // param.setThirdNotificationMessage();

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

        // 发送
        PushResult result = pushApi.push(param);
        log.info("result:{}", result);
    }

    @Test
    public void validatePush() {
        PushParam param = new PushParam();
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
        audience.setRegistrationIdList(Arrays.asList("13065ffa4e1a6cb3dc5"));
        // 指定目标
        // param.setAudience(audience);
        // 或者发送所有人
        param.setAudience(ApiConstants.Audience.ALL);

        // 指定平台
        param.setPlatform(Arrays.asList(Platform.android, Platform.ios));
        // 或者发送所有平台
        // param.setPlatform(ApiConstants.Platform.ALL);

        PushResult result = pushApi.validateSend(param);
        log.info("result:{}", result);
    }

    @Test
    public void withdrawMessage() {
        String messageId = "1095886";
        pushApi.withdrawMessage(messageId);
    }

    @Test
    public void getQuota() {
        QuotaGetResult result = pushApi.getQuota();
        log.info("result:{}", result);
    }

}
