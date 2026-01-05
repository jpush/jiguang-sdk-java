package cn.jiguang.app.api;

import cn.jiguang.sdk.api.DeviceApi;
import cn.jiguang.sdk.bean.device.*;
import cn.jiguang.sdk.exception.ApiErrorException;
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
    public void getDevice() throws ApiErrorException {
        String registrationId = "1507bfd3f6f7aaf8781";
        DeviceGetResult result = deviceApi.getDevice(registrationId);
        log.info("result:{}", result);
    }

    @Test
    public void setDevice() throws ApiErrorException {
        String registrationId = "1507bfd3f6f7aaf8781";
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
    public void clearDevice() throws ApiErrorException {
        String registrationId = "1507bfd3f6f7aaf8781";
        boolean clearTag = true;
        boolean clearAlias = false;
        boolean clearMobile = false;
        // 有3个重载方法，按需传参，需要清空传true
        DeviceClearParam param = DeviceClearParam.of(clearTag);
        // DeviceClearParam param = DeviceClearParam.of(clearTag, clearAlias);
        // DeviceClearParam param = DeviceClearParam.of(clearTag, clearAlias, clearMobile);
        deviceApi.clearDevice(registrationId, param);
    }

    @Test
    public void getAlias() throws ApiErrorException {
        String alias = "13111111111";
        AliasGetResult result = deviceApi.getAlias(alias);
        log.info("result:{}", result);
    }

    @Test
    public void deleteAlias() throws ApiErrorException {
        String alias = "13111111111";
        deviceApi.deleteAlias(alias);
    }

    @Test
    public void getTag() throws ApiErrorException {
        String registrationId = "1104a89793af2cfc030";
        String tag = "13111111111";
        TagGetResult result = deviceApi.getTag(tag, registrationId);
        log.info("result:{}", result);
    }

    @Test
    public void setTag() throws ApiErrorException {
        String tag = "13111111111";
        TagSetParam.RegistrationIds registrationIds = new TagSetParam.RegistrationIds();
        registrationIds.setAdd(Arrays.asList("1104a89793af2cfc030", "1104a89793af2cfc030"));
        // registrationIds.setRemove(Arrays.asList("1104a89793af2cfc030", "1104a89793af2cfc030"));
        TagSetParam param = new TagSetParam();
        param.setRegistrationIds(registrationIds);
        deviceApi.setTag(tag, param);
    }

    @Test
    public void deleteTag() throws ApiErrorException {
        String tag = "13111111111";
        deviceApi.deleteTag(tag);
    }

    @Test
    public void getDeviceStatus() throws ApiErrorException {
        DeviceStatusGetResult result = deviceApi.getDeviceStatus(Arrays.asList("18171adc023d94a7b6e", "18171adc023d94a7b6e"));
        log.info("result:{}", result);
    }

}
