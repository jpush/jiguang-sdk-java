package cn.jiguang.sdk.bean.push.message.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdNotificationMessage {
    /**
     * 补发通知标题，如果为空则默认为应用名称。
     */
    @JsonProperty("title")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    /**
     * 补发通知的内容，如果存在 notification_3rd 这个 key，content 字段不能为空，且值不能为空字符串。
     */
    @JsonProperty("content")
    private String content;

    /**
     * 使用 intent 里的 url 指定点击通知栏后跳转的目标页面;
     * SDK＜422 的版本此字段值仅对走华硕通道和极光自有通道下发生效，不影响请求走其它厂商通道。
     * SDK≥422 的版本，API 推送时建议填写 intent 字段，否则点击通知可能无跳转动作。此字段支持以下三种类型:
     * 1. 跳转到目标页:
     * intent:#Intent;action=action 路径;component= 包名 /Activity 全名;end
     * （OPPO 和 FCM 通道必须传 "action 路径", 其他厂商必须传 "Activity 全名", 否则将出现对应厂商无法跳转问题）
     * 2. 跳转到 deeplink 地址:
     * scheme://test?key1=val1&key2=val2
     * 3. 应用首页: intent:#Intent;action=android.intent.action.MAIN;end (固定为此地址)
     */
    @JsonProperty("intent")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Intent intent;

    /**
     * 用于指定开发者想要打开的 activity，值为 activity 节点的 “android:name”属性值。
     * 适配华为、小米、vivo 厂商通道跳转。
     * Jpush SDK≥V4.2.2，可不再填写本字段，仅设置 intent 字段即可。
     */
    @JsonProperty("uri_activity")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uriActivity;

    /**
     * 用于指定开发者想要打开的 activity，值为 "activity"-"intent-filter"-"action" 节点的 "android:name" 属性值。
     * 适配 oppo、fcm 跳转。
     * Jpush SDK≥V4.2.2，可不再填写本字段，仅设置 intent 字段即可，但若需兼容旧版 SDK 必须填写该字段
     */
    @JsonProperty("uri_action")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uriAction;

    /**
     * 桌面图标对应的应用入口 Activity 类，比如“com.test.badge.MainActivity”。
     * 仅华为和荣耀通道推送时生效，此值如果填写非主 Activity 类，以厂商限制逻辑为准。
     * 若需要实现角标累加功能，需配合 badge_add_num 使用，二者需要共存，缺少其一不可。
     * 若需要实现角标固定值功能，需配合 badge_set_num 使用，二者需要共存，缺少其一不可。
     */
    @JsonProperty("badge_class")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String badgeClass;

    /**
     * 设置角标数字累加值，在原角标的基础上进行累加，取值范围为：1-99。
     * 此属性目前仅针对华为 EMUI 8.0 及以上、小米 MIUI 6 及以上、vivo、荣耀设备走厂商通道时生效。
     * 此字段如果不填，表示不改变角标数字（小米设备由于系统控制，不论推送走极光通道下发还是厂商通道下发，即使不传递依旧是默认 +1 的效果）。
     * 若设置了取值范围内的数字，下一条通知栏消息配置的 badge_add_num 数据会和原角标数量进行相加，建议取值为1。
     * 举例：badge_add_num 取值为1，原角标数为2，发送此角标消息后，应用角标数显示为3。
     * 针对华为/荣耀通道，如果 badge_set_num 与 badge_add_num 同时存在，则以 badge_set_num 为准。
     */
    @JsonProperty("badge_add_num")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer badgeAddNumber;

    /**
     * 设置角标数字固定值，取值范围为：0-99。
     * 此属性目前仅针对华为 EMUI 8.0 及以上、荣耀设备走厂商通道时生效，如果 badge_set_num 与 badge_add_num 同时存在，则以 badge_set_num 为准。
     * 取值范围为：0-99，若设置了取值范围内的数字，对应下一条通知栏消息配置的 badge_set_num 数字则为角标数值，举例：badge_set_num 取值为 1，无论应用之前角标数为多少，发送此角标消息后，应用角标数均显示为1。
     */
    @JsonProperty("badge_set_num")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer badgeSetNumber;

    /**
     * 填写 Android 工程中 /res/raw/ 路径下铃声文件名称，无需文件名后缀。
     * 注意：针对 Android 8.0 以上，当传递了 channel_id 时，此属性不生效。
     */
    @JsonProperty("sound")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sound;

    /**
     * 根据 channel ID 来指定通知栏展示效果，不超过 1000 字节。
     * Android 8.0 开始可以进行 NotificationChannel 配置:https://docs.jiguang.cn/jpush/client/Android/android_api#notificationchannel-%E9%85%8D%E7%BD%AE
     */
    @JsonProperty("channel_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String channelId;

    /**
     * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
     * 扩展字段；这里自定义 JSON 格式的 Key / Value 信息，以供业务使用。
     */
    @JsonProperty("extras")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> extras;

    @Data
    public static class Intent {
        @JsonProperty("url")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String url;
    }
}
