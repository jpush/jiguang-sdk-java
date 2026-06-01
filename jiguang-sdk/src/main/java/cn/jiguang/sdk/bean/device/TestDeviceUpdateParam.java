package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TestDeviceUpdateParam {

    @JsonProperty("registration_id")
    private String registrationId;

    @JsonProperty("device_name")
    private String deviceName;

}
