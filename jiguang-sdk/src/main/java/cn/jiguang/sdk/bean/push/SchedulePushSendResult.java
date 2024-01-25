package cn.jiguang.sdk.bean.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SchedulePushSendResult {

    @JsonProperty("schedule_id")
    private String scheduleId;

    @JsonProperty("name")
    private String name;

}
