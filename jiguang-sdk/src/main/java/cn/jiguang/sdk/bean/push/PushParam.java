package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.push.audience.Audience;
import cn.jiguang.sdk.bean.push.message.custom.CustomMessage;
import cn.jiguang.sdk.bean.push.message.notification.NotificationMessage;
import cn.jiguang.sdk.bean.push.message.sms.SmsMessage;
import cn.jiguang.sdk.bean.push.options.Options;
import cn.jiguang.sdk.enums.platform.Platform;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushParam {
    @JsonProperty("cid")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cid;

    /**
     * 两种格式
     * 字符串："all"
     * {@link Audience}对象: {"tag":[],"tag_and":[],"tag_not":[],"alias":[],"registration_id":[]}
     */
    @JsonProperty("audience")
    private Object audience;

    /**
     * 两种格式
     * 字符串："all"
     * {@link Platform}数组：["android","ios","hmos","web","windows"]
     */
    @JsonProperty("platform")
    private Object platform;

    /**
     * 推送参数
     */
    @JsonProperty("options")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Options options;

    /**
     * 通知内容体,是被推送到客户端的内容。
     * 与 message 一起二者必须有其一，可以二者并存。
     */
    @JsonProperty("notification")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NotificationMessage notification;

    /**
     * 消息内容体,是被推送到客户端的内容。
     * 与 notification 一起二者必须有其一，可以二者并存。
     */
    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CustomMessage custom;

    /**
     * 短信渠道补充送达内容体
     */
    @JsonProperty("sms_message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SmsMessage smsMessage;

    /**
     * 回执
     */
    @JsonProperty("callback")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> callback;
}
