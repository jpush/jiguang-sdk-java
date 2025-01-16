package cn.jiguang.sdk.bean.push;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushResult {

    @JsonProperty("sendno")
    private String sendNo;

    @JsonProperty("msg_id")
    private String messageId;

}
