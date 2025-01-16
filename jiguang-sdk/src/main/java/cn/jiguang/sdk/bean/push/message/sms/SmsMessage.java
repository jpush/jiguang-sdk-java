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
     * 这里的Object，可以是基础数据类型
     * JSON 格式的可选参数
     * mobile：String 或者 {{phoneno}}；String 用于手动指定目标手机号码；{{phoneno}} 会使用已设置的手机号码填充。
     * temp_id：短信模版 id，long 类型。
     * temp_para：短信模板中的参数；k/v 对，会将模板中的 {{k}} 替换为 v。
     * sign：签名 ID，int 类型，该字段为空则使用应用默认签名。
     * 示例：{"mobile":"{{phoneno}}","temp_id":146465,"temp_para":{"date":"111"}}
     */
    @JsonProperty("content")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Map<String, Object> content;

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
}
