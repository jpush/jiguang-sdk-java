package cn.jiguang.sdk.bean.push.batch;

import cn.jiguang.sdk.bean.push.callback.Callback;
import cn.jiguang.sdk.bean.push.message.custom.CustomMessage;
import cn.jiguang.sdk.bean.push.message.notification.NotificationMessage;
import cn.jiguang.sdk.bean.push.message.notification.ThirdNotificationMessage;
import cn.jiguang.sdk.bean.push.message.sms.SmsMessage;
import cn.jiguang.sdk.bean.push.options.Options;
import cn.jiguang.sdk.enums.platform.Platform;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BatchPushParam {

    /**
     * 此处填写的是 regId 值或者 alias 值
     */
    @JsonProperty("target")
    private String target;

    /**
     * 两种格式
     * 字符串："all"
     * {@link Platform}数组：["android","ios","hmos","quickapp"]
     */
    @JsonProperty("platform")
    private Object platform;

    @JsonProperty("options")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Options options;

    @JsonProperty("notification")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NotificationMessage notification;

    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CustomMessage custom;

    @JsonProperty("sms_message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SmsMessage smsMessage;

    @JsonProperty("callback")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Callback callback;

    /**
     * 自定义消息转厂商通知内容体。
     * 与 message 一起使用。
     */
    @JsonProperty("notification_3rd")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ThirdNotificationMessage thirdNotificationMessage;

}
