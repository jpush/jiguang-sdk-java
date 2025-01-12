package cn.jiguang.sdk.bean.push.message.liveactivity;

import cn.jiguang.sdk.enums.event.Event;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class LiveActivityMessage {

    @JsonProperty("ios")
    private LiveActivityIOS iOS;

    @Data
    public static class LiveActivityIOS {
        /**
         * 必填
         * 创建：“start”，更新：“update”，结束："end"；必填
         */
        @JsonProperty("event")
        private Event event;

        /**
         * 可选
         * 实时活动类型，开发者自定义值，当event=start时该参数必填
         */
        @JsonProperty("attributes-type")
        private String attributesType;

        /**
         * 必填
         * 实时活动动态内容，需与客户端 SDK 值匹配（对应 Apple 官方的content-state 字段）
         * 这里的Object，可以是基础数据类型
         */
        @JsonProperty("content-state")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> contentState;

        /**
         * 可选
         */
        @JsonProperty("alert")
        private LiveActivityAlertIOS alert;

        /**
         * 实时活动结束展示时间
         */
        @JsonProperty("dismissal-date")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long dismissalDate;

        /**
         * 可选
         * 实时活动显示过期时间，如果该时间小于当前时间，实时活动将不会更新
         */
        @JsonProperty("stale-date")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long staleDate;

        /**
         * 可选
         * 这里的Object，可以是基础数据类型
         */
        @JsonProperty("attributes")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> attributes;

        /**
         * 可选
         * 实时活动在灵动岛上展示的优先级，取值[1,100],该值和实时活动的重要性呈正相关，不填默认为最高
         */
        @JsonProperty("relevance-score")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer relevanceScore;

        /**
         * 可选
         * 为5或10 ，不填默认为10。因为时候活动通知每小时是有频控限制的，apns-priority=5的通知将不消耗苹果厂商频控配额，当超出频控上限，推送通知将被限制
         */
        @JsonProperty("apns-priority")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer apnsPriority;
    }

    @Data
    public static class LiveActivityAlertIOS {
        /**
         * 可选
         * 提示音
         */
        @JsonProperty("sound")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String sound;

        /**
         * 可选
         * 显示到Apple Watch的消息标题
         */
        @JsonProperty("title")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String title;

        /**
         * 可选
         * 显示到Apple Watch的消息内容
         */
        @JsonProperty("body")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String body;
    }

}
