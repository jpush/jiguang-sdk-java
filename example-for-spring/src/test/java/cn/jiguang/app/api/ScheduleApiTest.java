package cn.jiguang.app.api;

import cn.jiguang.sdk.api.ScheduleApi;
import cn.jiguang.sdk.bean.push.PushParam;
import cn.jiguang.sdk.bean.push.audience.Audience;
import cn.jiguang.sdk.bean.push.message.notification.NotificationMessage;
import cn.jiguang.sdk.bean.schedule.*;
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
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ScheduleApiTest {

    @Autowired
    private ScheduleApi scheduleApi;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void schedulePush() {
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
        audience.setRegistrationIdList(Arrays.asList("140fe1da9eb96f97702"));
        // 指定目标
        param.setAudience(audience);
        // 或者发送所有人
        // param.setAudience(ApiConstants.Audience.ALL);

        // 指定平台
        param.setPlatform(Arrays.asList(Platform.android, Platform.ios));
        // 或者发送所有平台
        // param.setPlatform(ApiConstants.Platform.ALL);

        SchedulePushParam.Trigger.Single single = new SchedulePushParam.Trigger.Single();
        single.setTime(LocalDateTime.now().plusHours(1));

        SchedulePushParam.Trigger.Periodical periodical = new SchedulePushParam.Trigger.Periodical();
        periodical.setStartTime(LocalDateTime.now().plusDays(1));
        periodical.setEndTime(LocalDateTime.now().plusDays(7));
        periodical.setTime(LocalDateTime.now().toLocalTime());
        periodical.setTimeUnit(TimeUnit.DAY);
        periodical.setPoint(Arrays.asList("1", "2", "3"));

        SchedulePushParam.Trigger trigger = new SchedulePushParam.Trigger();
        // 定时任务
        trigger.setSingle(single);
        // 定期任务
        // trigger.setPeriodical(periodical);

        SchedulePushParam schedulePushParam = new SchedulePushParam();
        if (trigger.getSingle() != null) {
            schedulePushParam.setName("定时任务");
        }
        if (trigger.getPeriodical() != null) {
            schedulePushParam.setName("定期任务");
        }
        schedulePushParam.setEnabled(true);
        schedulePushParam.setTrigger(trigger);
        schedulePushParam.setPushParam(param);
        SchedulePushResult result = scheduleApi.schedulePush(schedulePushParam);
        log.info("result:{}", result);
    }

    @Test
    public void updateSchedulePush() {
        String scheduleId = "0701d923-abf9-43b6-994b-97e724be81e9";

        PushParam param = new PushParam();
        // 通知内容
        NotificationMessage.Android android = new NotificationMessage.Android();
        android.setAlert("this is android update alert");
        android.setTitle("this is android update title");
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setAlert("this is update alert");
        notificationMessage.setAndroid(android);
        param.setNotification(notificationMessage);

        // 目标人群
        Audience audience = new Audience();
        audience.setRegistrationIdList(Arrays.asList("140fe1da9eb96f97702"));
        // 指定目标
        param.setAudience(audience);
        // 或者发送所有人
        // param.setAudience(ApiConstants.Audience.ALL);

        // 指定平台
        param.setPlatform(Arrays.asList(Platform.android, Platform.ios));
        // 或者发送所有平台
        // param.setPlatform(ApiConstants.Platform.ALL);

        SchedulePushParam.Trigger.Single single = new SchedulePushParam.Trigger.Single();
        single.setTime(LocalDateTime.now().plusHours(1));

        SchedulePushParam.Trigger.Periodical periodical = new SchedulePushParam.Trigger.Periodical();
        periodical.setStartTime(LocalDateTime.now().plusDays(1));
        periodical.setEndTime(LocalDateTime.now().plusDays(7));
        periodical.setTime(LocalDateTime.now().toLocalTime());
        periodical.setTimeUnit(TimeUnit.DAY);
        periodical.setPoint(Arrays.asList("1", "2", "3", "4"));

        SchedulePushParam.Trigger trigger = new SchedulePushParam.Trigger();
        // 定时任务
        trigger.setSingle(single);
        // 定期任务
        // trigger.setPeriodical(periodical);

        SchedulePushParam schedulePushParam = new SchedulePushParam();
        if (trigger.getSingle() != null) {
            schedulePushParam.setName("定时任务");
        }
        if (trigger.getPeriodical() != null) {
            schedulePushParam.setName("定期任务");
        }
        schedulePushParam.setEnabled(true);
        schedulePushParam.setTrigger(trigger);
        schedulePushParam.setPushParam(param);

        scheduleApi.updateSchedulePush(scheduleId, schedulePushParam);
    }

    @Test
    public void listSchedulePush() {
        SchedulePushPageParam param = new SchedulePushPageParam();
        param.setPage(1);
        SchedulePushPageResult result = scheduleApi.listSchedulePush(param);
        log.info("result:{}", result);
    }

    @Test
    public void getSchedulePush() {
        String scheduleId = "0701d923-abf9-43b6-994b-97e724be81e9";
        List<SchedulePushParam> result = scheduleApi.getSchedulePush(scheduleId);
        log.info("result:{}", result);
    }

    @Test
    public void getSchedulePushDetail() {
        SchedulePushDetailGetParam param = new SchedulePushDetailGetParam();
        param.setScheduleId("0701d923-abf9-43b6-994b-97e724be81e9");
        SchedulePushDetailGetResult result = scheduleApi.getSchedulePushDetail(param);
        log.info("result:{}", result);
    }

    @Test
    public void deleteSchedulePush() {
        String scheduleId = "0701d923-abf9-43b6-994b-97e724be81e9";
        scheduleApi.deleteSchedulePush(scheduleId);
    }
}
