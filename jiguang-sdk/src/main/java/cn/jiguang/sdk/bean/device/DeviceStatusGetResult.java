package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
public class DeviceStatusGetResult {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, DeviceStatus> results = new HashMap<>();

    @JsonAnySetter
    public void handleUnknown(String registrationId, DeviceStatus deviceStatus) {
        results.put(registrationId, deviceStatus);
    }

    @Data
    public static class DeviceStatus {

        @JsonProperty("online")
        private Boolean online;

        @JsonProperty("last_online_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastOnlineTime;

    }

}
