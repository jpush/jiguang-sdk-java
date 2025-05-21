package cn.jiguang.sdk.bean.push.message.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * v2版本
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdNotificationMessage {

    @JsonProperty("android")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NotificationMessage.Android android;

    @JsonProperty("ios")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NotificationMessage.IOS ios;

    @JsonProperty("hmos")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NotificationMessage.HMOS hmos;
}
