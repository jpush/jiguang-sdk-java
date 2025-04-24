package cn.jiguang.sdk.bean.push.message.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 可设置字段，详情参考<a href="https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push">Notification</a>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationMessage {

    @JsonProperty("alert")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String alert;

    @JsonProperty("android")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Android android;

    @JsonProperty("ios")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private IOS ios;

    @JsonProperty("hmos")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HMOS hmos;

    @JsonProperty("quickapp")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private QuickApp quickApp;

    /**
     * iOS VOIP 功能。该类型推送支持和 iOS 的 Notification 通知并存，详见 voip:https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push#voip
     */
    @JsonProperty("voip")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> voip;

    @Data
    public static class Android {
        /**
         * 这里指定后会覆盖上级统一指定的 alert 信息。
         * 内容可以为空字符串，表示不展示到通知栏。
         * 各推送通道对此字段的限制详见 推送限制:https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push#%E7%9B%B8%E5%85%B3%E5%8F%82%E8%80%83
         */
        @JsonProperty("alert")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String alert;

        /**
         * 如果指定了，则通知里原来展示 App 名称的地方，将展示 title。
         * 各推送通道对此字段的限制详见 推送限制:https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push#%E7%9B%B8%E5%85%B3%E5%8F%82%E8%80%83
         */
        @JsonProperty("title")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String title;

        /**
         * Android SDK 可 设置通知栏样式:https://docs.jiguang.cn/jpush/client/Android/android_api#%E9%80%9A%E7%9F%A5%E6%A0%8F%E6%A0%B7%E5%BC%8F%E5%AE%9A%E5%88%B6-api
         * 根据样式 ID 来指定通知样式。
         * android 8.0 开始建议采用 NotificationChannel 配置:https://docs.jiguang.cn/jpush/client/Android/android_api#notificationchannel-%E9%85%8D%E7%BD%AE
         */
        @JsonProperty("builder_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer builderId;

        /**
         * 根据 channel ID 来指定通知栏展示效果，不超过 1000 字节。
         * Android 8.0 开始可以进行 NotificationChannel 配置:https://docs.jiguang.cn/jpush/client/Android/android_api#notificationchannel-%E9%85%8D%E7%BD%AE
         * options.third_party_channel 下的小米、OPPO 和华为厂商参数也有 channel_id 字段，若有填充，则优先使用，若无填充则以本字段定义为准。
         */
        @JsonProperty("channel_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String channelId;

        /**
         * 完全依赖 rom 厂商对 category 的处理策略。
         * 说明1：华为从 2023.09.15 开始基于《华为消息分类标准》 对其本地通知进行管控推送，参考：《华为本地通知频次及分类管控通知》 ，此字段值对应华为「本地通知」category取值，开发者通过极光服务发起推送时如果传递了此字段值，请务必按照华为官方要求传递，极光会自动适配华为本地通知importance取值，无需开发者额外处理。
         * 说明2：考虑到一次推送包含多个厂商用户的情况，建议此处传递的字段值要和您APP开发代码中创建的channel效果对应（category值一致），最好创建新的channelId，避免曾经已经创建了无法修改。
         * 官方category分类取值规则也可参考华为消息分类对应表
         */
        @JsonProperty("category")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String category;

        /**
         * 默认为 0，范围为 -2～2。
         * 说明1：华为从 2023.09.15 开始基于《华为消息分类标准》 对其本地通知进行管控推送，参考：《华为本地通知频次及分类管控通知》 ，开发者通过极光服务发起推送时，如果有传递此字段值，请注意此字段要和 category 同时使用；反之，如果传了category，没传递此值时极光会自动帮您适配处理优先级。
         * priority = -2 时，对应华为本地通知 importance 级别为 IMPORTANCE_MIN；priority = 0 时，对应华为本地通知 importance 级别为 IMPORTANCE_DEFAULT。
         * 官方消息优先级取值规则也可参考华为消息分类对应表
         * 极光取值 -2 ～-1 对应 FCM 取值 normal，极光取值 0~2 对应 FCM 取值 high。
         */
        @JsonProperty("priority")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer priority;

        /**
         * 用来指定通知栏样式类型，默认为 0。
         * 1：bigText
         * 2：Inbox
         * 3：bigPicture
         */
        @JsonProperty("style")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer style;

        /**
         * 可选范围为 -1～7 ，默认按照 -1 处理。
         * 即0111二进制，左数第二位代表 light,第三位代表 vibrate，第四位代表 sound。 0：不生效，1：生效。
         * 如： Notification.DEFAULT_ALL = -1 ，Notification.DEFAULT_SOUND = 1, Notification.DEFAULT_VIBRATE = 2, Notification.DEFAULT_LIGHTS = 4 的任意 “or” 组合。
         */
        @JsonProperty("alert_type")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer alertType;

        /**
         * 当 style = 1 时可用，内容会被通知栏以大文本的形式展示出来。
         * 若没有填充 厂商 big_text, 则也默认使用该 big_text 字段展示。
         * 支持 api 16 以上的 rom。
         */
        @JsonProperty("big_text")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String bigText;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         * 当 style = 2 时可用， json 的每个 key 对应的 value 会被当作文本条目逐条展示。
         * 若没有填充 厂商 inbox，则默认使用该 inbox 字段展示。
         * 支持 api 16 以上的 rom。
         */
        @JsonProperty("inbox")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> inbox;

        /**
         * 使用详情参见 设置大图片文档:https://docs.jiguang.cn/jpush/practice/set_icon#android%E3%80%82
         * 当 style = 3 时可用，目前支持 .jpg 和 .png 格式的图片。
         * 支持网络图片 url、本地图片的 path、 极光 media_id（推荐使用），如果是 http／https 的 url，会自动下载；如果要指定开发者准备的本地图片就填 sdcard 的相对路径。
         * 若没有填充 厂商 big_pic_path，则默认使用该字段展示。
         * 支持 api 16 以上的 rom。
         */
        @JsonProperty("big_pic_path")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String bigPicture;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         * 这里自定义 JSON 格式的 Key / Value 信息，以供业务使用。
         * 针对部分厂商跳转地址异常，可通过 third_url_encode 兼容处理，详情参考 厂商通道无法跳转问题分析:https://docs.jiguang.cn/jpush/faq/tech_faq#%E5%8E%82%E5%95%86%E9%80%9A%E9%81%93%E6%97%A0%E6%B3%95%E8%B7%B3%E8%BD%AC%EF%BC%9F
         * 当通知内容超过厂商的限制时，厂商通道会推送失败，可以在 extras 中配置 xx_content_forshort 参数传入对应厂商的通知内容，详情可展开表格查看。
         */
        @JsonProperty("extras")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> extras;

        /**
         * 使用详情参见 设置图标文档:https://docs.jiguang.cn/jpush/practice/set_icon#android%E3%80%82
         * 图标大小不超过 30 k（注：从 JPush Android SDK v4.0.0 版本开始，图片大小限制提升至 300 k）。
         * 支持网络图片 url、本地图片的 path、 极光 media_id（推荐使用），如果是 http／https 的 url，会自动下载；如果要指定开发者准备的本地图片就填 sdcard 的相对路径。
         * 此字段值，若是 media_id, 则对其它厂商通道生效，若非 media_id，则对走华硕通道和极光通道下发的消息生效，不影响请求走其它厂商通道。
         * 若没有填充 厂商 large_icon ，则默认使用该 large_icon 字段展示。
         */
        @JsonProperty("large_icon")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String largeIcon;

        /**
         * 使用详情参见 设置图标文档:https://docs.jiguang.cn/jpush/practice/set_icon#android%E3%80%82
         * 图标大小不超过 30 k （注：从 JPush Android SDK v4.0.0 版本开始，图片大小限制提升至 300 k）。
         * 支持以 http 或 https 开头的网络图片和通过极光图片上传接口得到的 media_id 值（推荐使用）。
         * 此字段值，若是 media_id, 则对其它厂商通道生效，若非 media_id，则对走华硕通道和极光通道下发的消息生效，不影响请求走其它厂商通道。
         * 若没有填充 厂商 small_icon_uri，则默认使用该 small_icon_uri 字段展示。
         */
        @JsonProperty("small_icon_uri")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String smallIcon;

        /**
         * 该字段仅对消息走极光通道下发生效。
         * 该字段能辅助解决部分设备小图标显示灰白情况，但最终还是依赖系统本身支持情况，建议开发者在设计UI图标时就做好适配工作。
         * 需要搭配 Android JPush SDK v5.5.0 及其以上版本使用。
         */
        @JsonProperty("icon_bg_color")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String iconBgColor;

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
         * 此属性目前仅针对华为 EMUI 8.0 及以上、小米 MIUI 6 及以上、vivo、荣耀设备生效。
         * 此字段如果不填，表示不改变角标数字（小米设备由于系统控制，不论推送走极光通道下发还是厂商通道下发，即使不传递依旧是默认 +1 的效果）。
         * 取值范围为：1-99，若设置了取值范围内的数字，下一条通知栏消息配置的 badge_add_num 数据会和原角标数量进行相加，建议取值为 1。
         * 举例：badge_add_num 取值为 1，原角标数为 2，发送此角标消息后，应用角标数显示为 3。
         * 针对华为 / 荣耀通道，若 badge_set_num 与 badge_add_num 同时存在，则以 badge_set_num 为准；若“badge_add_num”和“badge_set_num”都设置为空，则应用角标数字默认加1。
         */
        @JsonProperty("badge_add_num")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer badgeAddNumber;

        /**
         * 此属性目前仅针对华为 EMUI 8.0 及以上、荣耀设备走厂商通道时生效，若 badge_set_num 与 badge_add_num 同时存在，则以 badge_set_num 为准；若“badge_add_num”和“badge_set_num”都设置为空，则应用角标数字默认加1。
         * 取值范围为：0-99，若设置了取值范围内的数字，对应下一条通知栏消息配置的 badge_set_num 数字则为角标数值，举例：badge_set_num 取值为 1，无论应用之前角标数为多少，发送此角标消息后，应用角标数均显示为 1。
         */
        @JsonProperty("badge_set_num")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer badgeSetNumber;

        /**
         * 仅华为和荣耀通道推送时生效，此值如果填写非主 Activity 类，以厂商限制逻辑为准。
         * 若需要实现角标累加功能，需配合 badge_add_num 使用，二者需要共存，缺少其一不可。
         * 若需要实现角标固定值功能，需配合 badge_set_num 使用，二者需要共存，缺少其一不可。
         */
        @JsonProperty("badge_class")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String badgeClass;

        /**
         * 填写 Android 工程中 /res/raw/ 路径下铃声文件名称，无需文件名后缀.
         * 注意：针对 Android 8.0 以上，当传递了 channel_id 时，此属性不生效。
         */
        @JsonProperty("sound")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String sound;

        /**
         * 此属性不填写，SDK 默认立即展示；此属性填写，则以填写时间点为准才开始展示。
         * JPush Android SDK v3.5.0 版本开始支持。
         */
        @JsonProperty("show_begin_time")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime showBeginTime;

        /**
         * 此属性不填写，SDK 会一直展示；此属性填写，则以填写时间点为准，到达时间点后取消展示。
         * JPush Android SDK v3.5.0 版本开始支持。
         */
        @JsonProperty("show_end_time")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime showEndTime;

        /**
         * 值为 "1" 时，APP 在前台会弹出/展示通知栏消息。
         * 值为 "0" 时，APP 在前台不会弹出/展示通知栏消息。
         * 注：默认情况下 APP 在前台会弹出/展示通知栏消息，JPush Android SDK v3.5.8 版本开始支持。
         */
        @JsonProperty("display_foreground")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String displayForeground;

        @Data
        public static class Intent {
            @JsonProperty("url")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String url;
        }
    }

    @Data
    public static class IOS {
        /**
         * 这里的Object，可以是String类型，也可以是Map<String,Object>
         * 这里指定内容将会覆盖上级统一指定的 alert 信息。
         * 内容为空则不展示到通知栏。
         * 支持字符串形式也支持官方定义的 alert payload 结构，在该结构中包含 title 和 subtitle 等官方支持的 key。
         */
        @JsonProperty("alert")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object alert;

        /**
         * 这里的Object，可以是String类型，也可以是Map<String,Object>
         * 普通通知： string 类型，如果无此字段，则此消息无声音提示；有此字段，如果找到了指定的声音就播放该声音，否则播放默认声音，如果此字段为空字符串，iOS 7 为默认声音，iOS 8 及以上系统为无声音。说明：JPush 官方 SDK 会默认填充声音字段，提供另外的方法关闭声音，详情查看各 SDK 的源码。
         * 告警通知： JSON Object , 支持官方定义的 payload 结构，在该结构中包含 critical 、name 和 volume 等官方支持的 key 。
         * 自定义铃声说明：格式必须是 Linear PCM、MA4（IMA/ADPCM）、alaw，μLaw 的一种，将声频文件放到项目 bundle 目录中，且时长要求 30s 以下，否则就是系统默认的铃声，详见 自定义铃声:https://docs.jiguang.cn/jpush/practice/custom_ringtone#apns-%E9%80%9A%E9%81%93%E9%80%9A%E7%9F%A5%E5%AE%9E%E7%8E%B0
         */
        @JsonProperty("sound")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object sound;

        /**
         * 可设置为 N、+N、-N，N 的取值范围为 [0,99]。若上传的角标值 value 为 10，表示角标会设置为 N、10+N、10-N（值小于 0 时默认清除角标）。
         * 为 0 或空字符串，则表示清除角标。
         * 如果不填，表示不改变角标数字。
         * JPush 官方服务端 SDK 会默认填充 badge 值为 "+1"，详情参考：badge +1:https://community.jiguang.cn/article/464967
         */
        @JsonProperty("badge")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String badge;

        /**
         * 推送的时候携带 "content-available":true，说明是 Background Remote Notification，如果不携带此字段则是普通的 Remote Notification。
         * 详情参考：Background Remote Notification:https://docs.jiguang.cn/jpush/client/iOS/ios_new_fetures#ios-7-background-remote-notification
         */
        @JsonProperty("content-available")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Boolean contentAvailable;

        /**
         * iOS 10 新增的 Notification Service Extension 功能，用于上报每条 APNs 信息的送达状态，使用该功能需要客户端实现 Service Extension 接口 ，并在服务端使用 mutable-content 字段完成设置。
         * true：说明支持 iOS 10 的 UNNotificationServiceExtension 功能。
         * 如果不携带此字段则是普通的 Remote Notification，无法统计抵达数据。
         */
        @JsonProperty("mutable-content")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Boolean mutableContent;

        /**
         * iOS 8 开始支持，设置 APNs payload 中的 "category" 字段值。
         */
        @JsonProperty("category")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String category;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         * 这里自定义 Key / value 信息，以供业务使用，详情参考 如何设置右侧图标/大图片 和 iOS 通知点击跳转。
         */
        @JsonProperty("extras")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> extras;

        /**
         * ios 的远程通知通过该属性来对通知进行分组，同一个 thread-id 的通知归为一组。
         */
        @JsonProperty("thread-id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String threadId;

        /**
         * ios 15 的通知级别，取值只能是 active,critical,passive,time-sensitive 中的一个。
         * 详情参考：UNNotificationInterruptionLevel:https://developer.apple.com/documentation/usernotifications/unnotificationinterruptionlevel
         */
        @JsonProperty("interruption-level")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String interruptionLevel;
    }

    @Data
    public static class HMOS {
        /**
         * 这里指定后会覆盖上级统一指定的 alert 信息。
         * 内容不可以是空字符串，否则推送厂商会返回失败。
         */
        @JsonProperty("alert")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String alert;

        /**
         * 如果指定了，则通知里原来展示 App 名称的地方，将展示 title。否则使用WebPortal配置的默认title。
         */
        @JsonProperty("title")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String title;

        /**
         * 此字段由于厂商为必填字段，效果也完全依赖 rom 厂商对 category 的处理策略，请开发者务必填写。极光内部对此字段实际未进行必填校验，请开发者按照必填处理。
         * 此字段值对应官方「云端category」取值，开发者通过极光服务发起推送时如果传递了此字段值，请务必按照官方要求传递，官方category分类取值规则也可参考鸿蒙消息分类标准:https://developer.huawei.com/consumer/cn/doc/harmonyos-guides/push-noti-classification-0000001727885246#section1521814368537
         */
        @JsonProperty("category")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String category;

        /**
         * 要求传递网络地址，使用HTTPS协议，取值样例：https://example.com/image.png。
         * 图标大小不超过 30 k，图片长*宽<12800像素。
         */
        @JsonProperty("large_icon")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String largeIcon;

        /**
         * 支持跳转到应用首页、deeplink 地址和Action跳转三种类型：
         * 1. 跳转应用首页：固定 action.system.home
         * 2. 跳转到 deeplink 地址: scheme://test?key1=val1&key2=val2
         * 3. 跳转到 action 地址: com.test.action
         * 说明：此字段由于厂商为必填字段，请开发者务必填写。极光内部对此字段实际未进行必填校验，请开发者按照必填处理。
         */
        @JsonProperty("intent")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private HMOS.Intent intent;

        /**
         * 此字段如果不填，表示不改变角标数字
         * 取值范围为：1-99，若设置了取值范围内的数字，下一条通知栏消息配置的 badge_add_num 数据会和原角标数量进行相加，建议取值为 1。
         * 举例：badge_add_num 取值为 1，原角标数为 2，发送此角标消息后，应用角标数显示为 3。
         */
        @JsonProperty("badge_add_num")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer badgeAddNumber;

        /**
         * 此字段如果不填，表示不改变角标数字
         * 取值范围为：0-99，若设置了取值范围内的数字，对应下一条通知栏消息配置的 badge_set_num 数字则为角标数值，举例：badge_set_num 取值为 1，无论应用之前角标数为多少，发送此角标消息后，应用角标数均显示为 1。
         */
        @JsonProperty("badge_set_num")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer badgeSetNumber;

        /**
         * 测试消息标识：
         * false：正常消息（默认值）
         * true：测试消息
         */
        @JsonProperty("test_message")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Boolean isTest;

        /**
         * 输入一个唯一的回执 ID 指定本次下行消息的回执地址及配置，该回执 ID 可以在鸿蒙回执参数配置中查看。
         */
        @JsonProperty("receipt_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String receiptId;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         * 这里自定义 JSON 格式的 Key / Value 信息，以供业务使用。
         */
        @JsonProperty("extras")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> extras;

        /**
         * 通知栏样式类型，默认为0，0:普通样式 2:多文本样式
         */
        @JsonProperty("style")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer style;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         * 对应 style 的取值类型 2。
         */
        @JsonProperty("inbox")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> inbox;

        /**
         * 对应华为 push-type 字段，默认值 0，目前仅支持：0-通知消息，2-通知拓展消息，10-VoIP呼叫消息，其它值报错，voip消息与通知消息互斥，不可同时下发
         */
        @JsonProperty("push_type")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer pushType;

        /**
         * 对应华为 extraData 字段，当 push_type=2 或 push_type=10 时生效，此时是必填的，push_type=0时忽略此字段
         */
        @JsonProperty("extra_data")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String extraData;

        /**
         * 值为 "1" 时，APP 在前台会弹出/展示通知栏消息。
         * 值为 "0" 时，APP 在前台不会弹出/展示通知栏消息。
         * 注：默认情况下 APP 在前台会弹出/展示通知栏消息。
         */
        @JsonProperty("display_foreground")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String displayForeground;

        @Data
        public static class Intent {
            @JsonProperty("url")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String url;
        }
    }

    @Data
    public static class QuickApp {
        @JsonProperty("title")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String title;

        @JsonProperty("alert")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String alert;

        @JsonProperty("page")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String page;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         */
        @JsonProperty("extras")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> extras;
    }

}
