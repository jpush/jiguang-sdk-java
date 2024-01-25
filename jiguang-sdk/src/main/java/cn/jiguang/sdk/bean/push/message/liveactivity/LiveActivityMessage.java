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
        @JsonProperty("event")
        private Event event;

        @JsonProperty("content-state")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Map<String, Object> contentState;

        @JsonProperty("dismissal-date")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long dismissalDate;

        @JsonProperty("alert")
        private LiveActivityAlertIOS alert;

        @JsonProperty("timestamp")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long timestamp;
    }

    @Data
    public static class LiveActivityAlertIOS {
        @JsonProperty("sound")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String sound;

        @JsonProperty("title")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String title;

        @JsonProperty("alternate_title")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String alternateTitle;

        @JsonProperty("body")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String body;

        @JsonProperty("alternate_Body")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String alternateBody;
    }

}
