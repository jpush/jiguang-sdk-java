package cn.jiguang.sdk.bean.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDetailGetResult {

    @JsonProperty("msg_id")
    private String messageId;

    @JsonProperty("details")
    private Details details;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Details {
        @JsonProperty("notification")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private MessageStatus notification;

        @JsonProperty("message")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private MessageStatus custom;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MessageStatus {
        @JsonProperty("target")
        private Long target;

        @JsonProperty("sent")
        private Long sent;

        @JsonProperty("received")
        private Long received;

        @JsonProperty("display")
        private Long display;

        @JsonProperty("click")
        private Long click;

        @JsonProperty("sub_android")
        private Android android;

        @JsonProperty("sub_ios")
        private IOS iOS;

        @JsonProperty("sub_quickapp")
        private QuickApp quickApp;

        @JsonProperty("sub_hmos")
        private HMOS hmos;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Android {
        @JsonProperty("jg_android")
        private Status jiguang;

        @JsonProperty("xiaomi")
        private Status xiaomi;

        @JsonProperty("huawei")
        private Status huawei;

        @JsonProperty("honor")
        private Status honor;

        @JsonProperty("meizu")
        private Status meizu;

        @JsonProperty("oppo")
        private Status oppo;

        @JsonProperty("vivo")
        private Status vivo;

        @JsonProperty("asus")
        private Status asus;

        @JsonProperty("fcm")
        private Status fcm;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IOS {
        @JsonProperty("voip")
        private Status voip;

        @JsonProperty("apns")
        private Status apns;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class QuickApp {
        @JsonProperty("quick_jg")
        private Status jiguang;

        @JsonProperty("quick_xiaomi")
        private Status xiaomi;

        @JsonProperty("quick_huawei")
        private Status huawei;

        @JsonProperty("quick_oppo")
        private Status oppo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class HMOS {
        @JsonProperty("hmpns")
        private Status hmpns;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Status {
        @JsonProperty("target")
        private Long target;

        @JsonProperty("sent")
        private Long sent;

        @JsonProperty("received")
        private Long received;

        @JsonProperty("display")
        private Long display;

        @JsonProperty("click")
        private Long click;
    }

}
