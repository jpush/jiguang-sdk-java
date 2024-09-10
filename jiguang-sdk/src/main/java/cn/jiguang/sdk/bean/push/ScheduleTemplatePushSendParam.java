package cn.jiguang.sdk.bean.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ScheduleTemplatePushSendParam extends TemplatePushSendParam {

    @JsonProperty("schedule_name")
    private String scheduleName;

    @JsonProperty("trigger")
    private SchedulePushSendParam.Trigger trigger;

}
