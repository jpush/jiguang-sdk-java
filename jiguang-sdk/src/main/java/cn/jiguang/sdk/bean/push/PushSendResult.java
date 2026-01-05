package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PushSendResult extends BaseResult {

    @JsonProperty("sendno")
    private String sendNo;

    @JsonProperty("msg_id")
    private String messageId;

}
