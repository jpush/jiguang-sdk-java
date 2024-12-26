package cn.jiguang.sdk.bean.push.message.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsMessage {
    /**
     * 短信发送的延迟时间，若在设定的时间内没有推送成功，则下发短信。
     * 设置为 0，表示立即发送短信，即通知和短信并发。
     * 设置不为 0，表示若在设定的时间内没有推送成功，则进行短信补发。
     * 单位为秒，不能超过 24 小时。
     * 该参数仅对 android 和 iOS 平台有效。
     */
    @JsonProperty("delay_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer delayTime;

    /**
     * 签名 ID，该字段为空则使用应用默认签名。
     */
    @JsonProperty("signid")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer signId;

    /**
     * 短信补充的内容模板 ID,没有填写该字段即表示不使用短信补充功能。
     */
    @JsonProperty("temp_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer tempId;

    /**
     * 短信模板中的参数。
     */
    @JsonProperty("temp_para")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> tempParams;

    /**
     * active_filter 字段用来控制是否对补发短信的用户进行活跃过滤。
     * 默认为 true ，做活跃过滤。
     * 为 false，则不做活跃过滤。
     */
    @JsonProperty("active_filter")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean activeFilter;
}
