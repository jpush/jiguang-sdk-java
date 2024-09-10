package cn.jiguang.sdk.bean.push.other;

import cn.jiguang.sdk.bean.push.audience.Audience;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class TemplateParam {

    /**
     * 两种格式
     * 字符串："all"
     * {@link Audience}对象: {"tag":[],"tag_and":[],"tag_not":[],"alias":[],"registration_id":[],"segment":[],"abtest":[],"live_activity_id":"","file":{"file_id":""}}
     */
    @JsonProperty("audience")
    private Object audience;

    @JsonProperty("keys")
    private Map<String, String> keys;

    @JsonProperty("trace_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String traceId;

}
