package cn.jiguang.sdk.bean.push.message.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsMessage {

    @JsonProperty("temp_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer tempId;

    @JsonProperty("signid")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer signId;

    @JsonProperty("delay_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer delayTime;

    @JsonProperty("active_filter")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean activeFilter;

    @JsonProperty("temp_para")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> tempParams;

}
