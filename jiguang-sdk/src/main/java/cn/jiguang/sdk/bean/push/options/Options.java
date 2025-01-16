package cn.jiguang.sdk.bean.push.options;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
     * 单点推送启用开关
     * 当启用（true）时，使用alias推送，相同应用只有最后设置某alias的设备可以收到消息。
     * ps:portal控制台设置的开关是默认配置，当没有该选项时才启用。
     */
    @JsonProperty("unique_alias")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean uniqueAlias;

    /**
     * Android厂商通道配置信息
     * 仅针对配置了厂商通道的用户有效参数详情查看 third_party_channel
     */
    @JsonProperty("third_party_channel")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> thirdPartyChannel;

    /**
     * 地理围栏信息
     * 用于指定地理围栏的地理位置范围以及地理围栏的有效期。
     */
    @JsonProperty("geofence")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> geofence;

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
     * 消息场景标识
     * 由开发者自定义的任意值，如果想要单独过滤特定消息场景的数据，则一定要传递此值，且尽量固定。
     * 支持字母（区分大小写）、数字、下划线、汉字、特殊字符@!#$&*+=.|￥，最长支持40个字符
     * 后续通过概况-消息场景标识入口进行漏斗数据和折损情况的查询。
     */
    @JsonProperty("message_scenario_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageScenarioCode;
}
