package cn.jiguang.app.api;

import cn.jiguang.sdk.api.DeviceApi;
import cn.jiguang.sdk.bean.device.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@Slf4j
@SpringBootTest()
@RunWith(SpringRunner.class)
public class DeviceApiTest {

    @Autowired
    private DeviceApi deviceApi;

    @Test
    public void getDevice() {
        String registrationId = "1104a89793af2cfc030";
        DeviceGetResult result = deviceApi.getDevice(registrationId);
        log.info("result:{}", result);
    }

    @Test
    public void setDevice() {
        String registrationId = "1104a89793af2cfc030";
        DeviceSetParam.Tags tags = new DeviceSetParam.Tags();
        tags.setAdd(Arrays.asList("13111111111", "13222222222"));
        tags.setRemove(Arrays.asList("13333333333", "13444444444"));
        DeviceSetParam param = new DeviceSetParam();
        param.setTags(tags);
        param.setAlias("13111111111");
        param.setMobile("13111111111");
        deviceApi.setDevice(registrationId, param);
    }

    @Test
    public void getAlias() {
        String alias = "13111111111";
        AliasGetResult result = deviceApi.getAlias(alias);
        log.info("result:{}", result);
    }

    @Test
    public void deleteAlias() {
        String alias = "13111111111";
        deviceApi.deleteAlias(alias);
    }

    @Test
    public void getTag() {
        String registrationId = "1104a89793af2cfc030";
        String tag = "13111111111";
        TagGetResult result = deviceApi.getTag(tag, registrationId);
        log.info("result:{}", result);
    }

    @Test
    public void setTag() {
        String tag = "13111111111";
        TagSetParam.RegistrationIds registrationIds = new TagSetParam.RegistrationIds();
        registrationIds.setAdd(Arrays.asList("1104a89793af2cfc030", "1104a89793af2cfc030"));
        TagSetParam param = new TagSetParam();
        param.setRegistrationIds(registrationIds);
        deviceApi.setTag(tag, param);
    }

    @Test
    public void deleteTag() {
        String tag = "13111111111";
        deviceApi.deleteTag(tag);
    }

}
