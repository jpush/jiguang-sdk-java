package cn.jiguang.sdk.bean.push.batch;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchPushSendResult {

    private Map<String, BatchPushResult> sendResult = new HashMap<>();

    @JsonAnySetter
    public void addResult(String key, BatchPushResult value) {
        sendResult.put(key, value);
    }

}
