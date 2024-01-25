package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.push.audience.Audience;
import cn.jiguang.sdk.bean.push.message.custom.CustomMessage;
import cn.jiguang.sdk.bean.push.message.liveactivity.LiveActivityMessage;
import cn.jiguang.sdk.bean.push.message.notification.NotificationMessage;
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
     * {@link Platform}数组：["android","ios","quickapp"]
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

    @JsonProperty("live_activity")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LiveActivityMessage liveActivity;

}
