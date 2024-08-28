package cn.jiguang.sdk.bean.push.batch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class BatchPushSendParam {

    @JsonProperty("pushlist")
    private Map<String, BatchPushParam> sendParam;

}
