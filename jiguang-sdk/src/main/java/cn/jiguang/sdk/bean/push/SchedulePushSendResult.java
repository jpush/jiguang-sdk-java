package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchedulePushSendResult extends BaseResult {

    @JsonProperty("schedule_id")
    private String scheduleId;

    @JsonProperty("name")
    private String name;

}
