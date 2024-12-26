package cn.jiguang.sdk.bean.push.options;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Options {
    /**
     * 纯粹用来作为 API 调用标识，API 返回时被原样返回，以方便 API 调用方匹配请求与返回。
     * 值为 0 表示该 messageid 无 sendno，所以字段取值范围为非 0 的 int。
     */
    @JsonProperty("sendno")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long sendNo;

    /**
     * 推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。
     * 默认 86400 （1 天），普通用户最长 3 天， VIP 用户最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到。
     * 该字段对 iOS 的 Notification 消息无效。
     */
    @JsonProperty("time_to_live")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long timeToLive;

    /**
     * 如果当前的推送要覆盖之前的一条推送，这里填写前一条推送的 msg_id 就会产生覆盖效果，即：
     * 该 msg_id 离线收到的消息是覆盖后的内容，即使该 msg_id Android 端用户已经收到，如果通知栏还未清除，则新的消息内容会覆盖之前这条通知。
     * 覆盖功能起作用的时限是：1 天，如果在覆盖指定时限内该 msg_id 不存在，则返回 1003 错误，提示不是一次有效的消息覆盖操作，当前的消息不会被推送。
     * 该字段仅对 Android 有效仅支持极光通道、小米通道、魅族通道、OPPO 通道、FCM 通道、荣耀通道和华为通道（EMUI10 及以上的设备）。
     */
    @JsonProperty("override_msg_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long overrideMsgId;

    /**
     * 该字段仅对 iOS 的 Notification 有效，如果不指定则为推送生产环境。注意：JPush 服务端 SDK 默认设置为推送 “开发环境”。
     * true：表示推送生产环境。
     * false：表示推送开发环境。
     */
    @JsonProperty("apns_production")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean apnsProduction;

    /**
     * APNs 新通知如果匹配到当前通知中心有相同 apns-collapse-id 字段的通知，则会用新通知内容来更新它，并使其置于通知中心首位。
     * collapse id 长度不可超过 64 bytes。
     */
    @JsonProperty("apns_collapse_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String apnsCollapseId;

    /**
     * 又名缓慢推送，把原本尽可能快的推送速度，降低下来，给定的 n 分钟内，均匀地向这次推送的目标用户推送；最大值为 1400。
     * 最多能同时存在 20 条定速推送。
     * 未设置则不是定速推送。
     */
    @JsonProperty("big_push_duration")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer bigPushDuration;

    /**
     * 参考：https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push#third_party_channel-%E8%AF%B4%E6%98%8E
     */
    @JsonProperty("third_party_channel")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> thirdPartyChannel;

    /**
     * 极光不对指定的消息类型进行判断或校准，会以开发者自行指定的消息类型适配 Android 厂商通道。不填默认为 0。
     * 0：代表运营消息。
     * 1：代表系统消息。
     * 此字段优先级最高，会覆盖 options.third_party_channel.vivo.classification 设置的值。
     */
    @JsonProperty("classification")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer classification;

    /**
     * 目标转化事件支持传递「自定义事件」和「极光预置事件」，目前支持Android和iOS平台（要求JPush SDK ≥ 5.0.0 ，且JCore ≥ 4.2.0），支持通知消息和应用内消息两种消息类型。
     * 自定义事件：需集成极光分析SDK，开发者在极光分析产品中自行创建的业务事件（如：加入购物车、浏览商品等），详情参考 如何创建自定义事件 和 SDK如何上报自定义事件
     * 极光预置事件：极光推送SDK默认支持，无需开发者创建，也无需集成极光分析SDK，系统已预置；目标支持的预置事件有2个：jg_app_show（应用切换到前台）、jg_app_hide（应用切换到后台）。
     * 代码示例：{"options": {"target_event": ["jg_app_show"]}}
     */
    @JsonProperty("target_event")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> targetEvent;

    /**
     * 测试消息标识，指定鸿蒙平台通知和自定义消息推送配置，优先级大于hmos通知体内的test_message字段
     * false：正常消息（默认值）
     * true：测试消息
     */
    @JsonProperty("test_message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isTest;

    /**
     * 华为回执ID，指定鸿蒙平台通知和自定义消息推送配置，优先级大于hmos通知体内的receipt_id字段
     */
    @JsonProperty("receipt_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String receiptId;

    /**
     * true-使用亮屏推送，false-不使用亮屏推送，默认值false。
     * 此功能为增值付费服务，需要额外申请权限。
     * 当使用亮屏推送时，建议同时设置need_backup=true。
     * 此功能仅支持单纯通知消息，不支持自定义消息或者通知+自定义消息推送，否则请求会返回 code 码 1035。
     * 此功能不支持定速推送，否则请求会返回 code 码 1035。
     * 亮屏推送支持的时间范围是每天 7:00 - 22:00
     * 亮屏推送对于Android厂商用户的下发策略固定为在线走极光，离线走厂商。
     */
    @JsonProperty("active_push")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean activePush;

    /**
     * true-使用亮屏兜底策略，false-不使用亮屏兜底策略，默认值false。
     * 若此字段指定为true，则active_push字段值必须为true。
     * 是否使用兜底策略主要是确认离线消息到期后的处理逻辑。
     * 当使用兜底策略下发时：如果是厂商用户（离线消息到期后 0-5 分钟之内通过厂商通道下发），如果是非厂商用户（离线消息到期后，如果用户是在线状态则直接下发；如果用户离线则丢弃）。
     * 例如上午 8 点推送此条消息，设置了离线时间 2 小时。在 8:00 - 10:00 内，设备亮屏则会触发消息下发。剩余未发送的用户，在到达10:00后，0-5 分钟之内剩余消息走厂商通道下发。
     * 当不使用兜底策略下发时：离线消息到期后未下发的直接丢弃，不区分是否厂商用户。
     */
    @JsonProperty("need_backup")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean needBackup;

    /**
     * 需先通过极光 WebPortal 创建计划标识值，创建步骤参考推送计划文档:https://docs.jiguang.cn/jpush/console/config_manage/push_plan
     */
    @JsonProperty("business_operation_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String business_operation_code;
}
