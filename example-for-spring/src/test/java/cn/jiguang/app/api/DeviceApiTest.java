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
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeviceApiTest {

    @Autowired
    private DeviceApi deviceApi;

    @Test
    public void getDevice() {
        String registrationId = "193e35f7e057954af4b";
        DeviceGetResult result = deviceApi.getDevice(registrationId);
        log.info("result:{}", result);
    }

    @Test
    public void setDevice() {
        String registrationId = "193e35f7e057954af4b";
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
        AliasGetParam param = new AliasGetParam();
        param.setAlias("13111111111");
        AliasGetResult result = deviceApi.getAlias(param);
        log.info("result:{}", result);
    }

    @Test
    public void deleteAlias() {
        AliasDeleteParam param = new AliasDeleteParam();
        param.setAlias("13111111111");
        deviceApi.deleteAlias(param);
    }

    @Test
    public void getTags() {
        TagsGetResult result = deviceApi.getTags();
        log.info("result:{}", result);
    }

    @Test
    public void getTag() {
        TagGetParam param = new TagGetParam();
        param.setTag("13111111111");
        param.setRegistrationId("193e35f7e057954af4b");
        TagGetResult result = deviceApi.getTag(param);
        log.info("result:{}", result);
    }

    @Test
    public void setTag() {
        String tag = "133";
        TagSetParam.RegistrationIds registrationIds = new TagSetParam.RegistrationIds();
        registrationIds.setAdd(Arrays.asList("193e35f7e057954af4b"));
        // registrationIds.setRemove(Arrays.asList("140fe1da9eb96f97702"));
        TagSetParam param = new TagSetParam();
        param.setRegistrationIds(registrationIds);
        deviceApi.setTag(tag, param);
    }

    @Test
    public void deleteTag() {
        TagDeleteParam param = new TagDeleteParam();
        param.setTag("133");
        deviceApi.deleteTag(param);
    }

    @Test
    public void getDeviceStatus() {
        List<DeviceStatusGetResult> result = deviceApi.getDeviceStatus(Arrays.asList("140fe1da9eb96f97702"));
        log.info("result:{}", result);
    }

    @Test
    public void setWebDevice(){
        String registrationId = "193e35f7e057954af4b";
        WebDeviceSetParam param = new WebDeviceSetParam();
        param.setTags(Arrays.asList("webTag"));
        param.setAlias("webAlias");
        deviceApi.setWebDevice(registrationId, param);
    }

}
