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
@SpringBootTest()
@RunWith(SpringRunner.class)
public class ReportApiTest {

    @Autowired
    private ReportApi reportApi;

    @Test
    public void getReceivedDetail() {
        List<ReceivedDetailGetResult> result = reportApi.getReceivedDetail(Arrays.asList("18101209671232831", "18101209671232832"));
        log.info("result:{}", result);
    }

    @Test
    public void getMessageStatus() {
        MessageStatusGetParam param = new MessageStatusGetParam();
        param.setDate(LocalDate.now());
        param.setMessageId(18101211308815829L);
        param.setRegistrationIds(Arrays.asList("140fe1da9fad2ee67f1", "140fe1da9fad2ee67f2"));
        Map<String, MessageStatusGetResult> result = reportApi.getMessageStatus(param);
        log.info("result:{}", result);
    }

    @Test
    public void getMessageDetail() {
        List<MessageDetailGetResult> result = reportApi.getMessageDetail(Arrays.asList("18101209671232831", "18101209671232832"));
        log.info("result:{}", result);
    }

    @Test
    public void MessageDetailGetResult() {
        UserDetailGetResult result = reportApi.getUserDetail(LocalDate.now().minusDays(60), 20);
        log.info("result:{}", result);
    }

}
