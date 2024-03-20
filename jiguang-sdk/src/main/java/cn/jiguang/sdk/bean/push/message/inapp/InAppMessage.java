package cn.jiguang.sdk.bean.push.message.inapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InAppMessage {

    @JsonProperty("inapp_message")
    private Boolean inAppMessage;

}
