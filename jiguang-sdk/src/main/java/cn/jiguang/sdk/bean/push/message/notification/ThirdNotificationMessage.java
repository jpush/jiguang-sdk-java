package cn.jiguang.sdk.bean.push.message.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdNotificationMessage {

    @JsonProperty("title")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("intent")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Intent intent;

    @JsonProperty("uri_activity")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uriActivity;

    @JsonProperty("uri_action")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uriAction;

    @JsonProperty("badge_class")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String badgeClass;

    @JsonProperty("badge_add_num")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer badgeAddNumber;

    @JsonProperty("badge_set_num")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer badgeSetNumber;

    @JsonProperty("sound")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sound;

    @JsonProperty("channel_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String channelId;

    /**
     * 这里的Object，可以是基础数据类型，也可以是Map<String,Object>
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
