package cn.jiguang.app.api;

import cn.jiguang.sdk.api.AdminApi;
import cn.jiguang.sdk.bean.admin.AppCreateParam;
import cn.jiguang.sdk.bean.admin.AppCreateResult;
import cn.jiguang.sdk.bean.admin.AppDeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest()
@RunWith(SpringRunner.class)
public class AdminApiTest {

    @Autowired
    private AdminApi adminApi;

    @Test
    public void createApp() {
        AppCreateParam param = new AppCreateParam();
        param.setAppName("jiguang-test");
        param.setPackageName("cn.jiguang.test");
        AppCreateResult result = adminApi.createApp(param);
        log.info("result:{}", result);
    }

    @Test
    public void deleteApp() {
        String appKey = "fbd202ec599b20ad95da9907";
        AppDeleteResult result = adminApi.deleteApp(appKey);
        log.info("result:{}", result);
    }

}
