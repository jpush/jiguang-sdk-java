package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.push.audience.Audience;
import cn.jiguang.sdk.bean.push.callback.Callback;
import cn.jiguang.sdk.bean.push.message.custom.CustomMessage;
import cn.jiguang.sdk.bean.push.message.inapp.InAppMessage;
import cn.jiguang.sdk.bean.push.message.liveactivity.LiveActivityMessage;
import cn.jiguang.sdk.bean.push.message.notification.NotificationMessage;
import cn.jiguang.sdk.bean.push.message.notification.ThirdNotificationMessage;
import cn.jiguang.sdk.bean.push.message.sms.SmsMessage;
import cn.jiguang.sdk.bean.push.options.Options;
import cn.jiguang.sdk.enums.platform.Platform;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PushSendParam {
    @JsonProperty("cid")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cid;

    /**
     * 两种格式
     * 字符串："all"
     * {@link Audience}对象: {"tag":[],"tag_and":[],"tag_not":[],"alias":[],"registration_id":[],"segment":[],"abtest":[],"live_activity_id":"","file":{"file_id":""}}
     */
    @JsonProperty("audience")
    private Object audience;

    /**
     * 两种格式
     * 字符串："all"
     * {@link Platform}数组：["android","ios","hmos","quickapp"]
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
     * 实时活动内容体。
     * 不可与 notification 或 message 等并存。
     */
    @JsonProperty("live_activity")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LiveActivityMessage liveActivity;

    /**
     * 面向于通知栏消息类型，需搭配notification参数一起使用，对于通知权限关闭的用户可设置启用此功能。
     * 不可与 message 同时并存。
     */
    @JsonProperty("inapp_message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private InAppMessage inApp;

    /**
     * 自定义消息转厂商通知内容体。
     * 与 message 一起使用。
     */
    @JsonProperty("notification_3rd")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ThirdNotificationMessage thirdNotificationMessage;

    /**
     * 短信渠道补充送达内容体
     */
    @JsonProperty("sms_message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SmsMessage smsMessage;

    /**
     * 回调参数
     */
    @JsonProperty("callback")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Callback callback;
}
