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

    @JsonProperty("alternate_alert")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String alternateAlert;

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

    @JsonProperty("voip")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> voip;

    @Data
    public static class Android {
        @JsonProperty("alert")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String alert;

        @JsonProperty("title")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String title;

        @JsonProperty("builder_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer builderId;

        @JsonProperty("channel_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String channelId;

        @JsonProperty("category")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String category;

        @JsonProperty("priority")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer priority;

        @JsonProperty("style")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer style;

        @JsonProperty("alert_type")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer alertType;

        @JsonProperty("big_text")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String bigText;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         */
        @JsonProperty("inbox")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> inbox;

        @JsonProperty("big_pic_path")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String bigPicture;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         */
        @JsonProperty("extras")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> extras;

        @JsonProperty("large_icon")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String largeIcon;

        @JsonProperty("small_icon_uri")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String smallIcon;

        @JsonProperty("intent")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Intent intent;

        @JsonProperty("uri_activity")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String uriActivity;

        @JsonProperty("uri_action")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String uriAction;

        @JsonProperty("badge_add_num")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer badgeAddNumber;

        @JsonProperty("badge_set_num")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer badgeSetNumber;

        @JsonProperty("badge_class")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String badgeClass;

        @JsonProperty("sound")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String sound;

        @JsonProperty("show_begin_time")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime showBeginTime;

        @JsonProperty("show_end_time")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime showEndTime;

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
         */
        @JsonProperty("alert")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object alert;

        /**
         * 这里的Object，可以是String类型，也可以是Map<String,Object>
         */
        @JsonProperty("sound")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object sound;

        @JsonProperty("badge")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String badge;

        @JsonProperty("content-available")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Boolean contentAvailable;

        @JsonProperty("mutable-content")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Boolean mutableContent;

        @JsonProperty("category")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String category;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         */
        @JsonProperty("extras")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> extras;

        @JsonProperty("thread-id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String threadId;

        @JsonProperty("interruption-level")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String interruptionLevel;
    }

    @Data
    public static class HMOS {
        @JsonProperty("alert")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String alert;

        @JsonProperty("title")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String title;

        @JsonProperty("category")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String category;

        @JsonProperty("large_icon")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String largeIcon;

        @JsonProperty("intent")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Android.Intent intent;

        @JsonProperty("badge_add_num")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer badgeAddNumber;

        /**
         * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
         */
        @JsonProperty("extras")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> extras;

        @JsonProperty("test_message")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Boolean isTest;

        @JsonProperty("receipt_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String receiptId;

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
