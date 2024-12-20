package cn.jiguang.sdk.bean.push.other;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemplateResult {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonProperty("message_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageId;

    @JsonProperty("schedule_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String scheduleId;

    @JsonProperty("trace_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String traceId;

}
