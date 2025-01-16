package cn.jiguang.sdk.bean.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulePushResult {

    @JsonProperty("schedule_id")
    private String scheduleId;

    @JsonProperty("name")
    private String name;

}
