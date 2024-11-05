package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeviceStatusGetParam {

    @JsonProperty("registration_ids")
    List<String> registrationIds;

}
