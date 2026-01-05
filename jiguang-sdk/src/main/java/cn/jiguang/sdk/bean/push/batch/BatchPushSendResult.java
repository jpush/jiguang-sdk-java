package cn.jiguang.sdk.bean.push.batch;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchPushSendResult extends BaseResult {

    private Map<String, BatchPushResult> sendResult = new HashMap<>();

    @JsonAnySetter
    public void addResult(String key, BatchPushResult value) {
        sendResult.put(key, value);
    }

}
