package cn.jiguang.sdk.bean.push.other;

import cn.jiguang.sdk.bean.push.PushSendParam;
import cn.jiguang.sdk.bean.push.audience.Audience;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SM2Push {

    /**
     * 两种格式
     * 字符串："all"
     * {@link Audience}对象: {"tag":[],"tag_and":[],"tag_not":[],"alias":[],"registration_id":[],"segment":[],"abtest":[],"live_activity_id":"","file":{"file_id":""}}
     */
    @JsonProperty("audience")
    private Object audience;

    /**
     * 完整的消息体
     */
    @JsonProperty("payload")
    private String payload;

}
