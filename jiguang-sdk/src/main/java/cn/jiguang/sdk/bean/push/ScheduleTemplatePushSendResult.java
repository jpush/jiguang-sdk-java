package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.push.other.TemplateResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleTemplatePushSendResult {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Data data;

    @lombok.Data
    public static class Data {

        @JsonProperty("schedule_list")
        private List<TemplateResult> results;

    }

}
