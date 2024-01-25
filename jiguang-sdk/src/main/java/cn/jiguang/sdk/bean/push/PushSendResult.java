package cn.jiguang.sdk.bean.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PushSendResult {

    @JsonProperty("sendno")
    private String sendNo;

    @JsonProperty("msg_id")
    private String messageId;

}
