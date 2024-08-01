package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.exception.ApiErrorException;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GroupPushSendResult {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, ApiErrorException.ApiError.Error> errors = new HashMap<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, PushSendResult> successes = new HashMap<>();

    @JsonProperty("group_msgid")
    private String groupMessageId;

    @JsonAnySetter
    public void handleUnknown(String key, JsonNode value) {
        if (key.equals("group_msgid")) {
            setGroupMessageId(value.asText());
        } else if (value.has("error")) {
            ApiErrorException.ApiError.Error errorDetail = new ApiErrorException.ApiError.Error();
            errorDetail.setCode(value.get("error").get("code").asInt());
            errorDetail.setMessage(value.get("error").get("message").asText());
            errors.put(key, errorDetail);
        } else if (value.has("msg_id")) {
            PushSendResult successDetail = new PushSendResult();
            successDetail.setMessageId(value.get("msg_id").asText());
            successDetail.setSendNo(value.get("sendno").asText());
            successes.put(key, successDetail);
        }
    }

}
