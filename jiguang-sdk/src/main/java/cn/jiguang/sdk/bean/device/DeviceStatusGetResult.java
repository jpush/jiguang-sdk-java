package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceStatusGetResult {

    @JsonProperty("regid")
    private String registrationId;

    /**
     * true: 10 分钟之内在线；
     * false: 10 分钟之内不在线；
     */
    @JsonProperty("online")
    private Boolean online;

    /**
     * 当 online 为 true 时，该字段不返回;
     * 当 online 为 false，且该字段不返回时，则表示最后一次在线时间是两天之前；
     */
    @JsonProperty("last_online_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastOnlineTime;
}
