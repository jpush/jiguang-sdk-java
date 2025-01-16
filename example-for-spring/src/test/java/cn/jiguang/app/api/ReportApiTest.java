package cn.jiguang.app.api;

import cn.jiguang.sdk.api.ReportApi;
import cn.jiguang.sdk.bean.report.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReportApiTest {

    @Autowired
    private ReportApi reportApi;

    @Test
    public void getReceivedDetail() {
        ReceivedDetailGetParam param = new ReceivedDetailGetParam();
        param.setMsgIds(Arrays.asList("2134796"));
        List<ReceivedDetailGetResult> result = reportApi.getReceivedDetail(param);
        log.info("result:{}", result);
    }

    @Test
    public void getMessageStatus() {
        MessageStatusGetParam param = new MessageStatusGetParam();
        param.setDate(LocalDate.now());
        param.setMessageId(2134793L);
        param.setRegistrationIds(Arrays.asList("193e35f7e057954af4b"));
        Map<String, MessageStatusGetResult> result = reportApi.getMessageStatus(param);
        log.info("result:{}", result);
    }

    @Test
    public void getMessageDetail() {
        MessageDetailGetParam param = new MessageDetailGetParam();
        param.setMsgIds(Arrays.asList("2134796"));
        List<MessageDetailGetResult> result = reportApi.getMessageDetail(param);
        log.info("result:{}", result);
    }

    @Test
    public void MessageDetailGetResult() {
        UserDetailGetResult result = reportApi.getUserDetail(LocalDate.now().minusDays(20), 20);
        log.info("result:{}", result);
    }

}
