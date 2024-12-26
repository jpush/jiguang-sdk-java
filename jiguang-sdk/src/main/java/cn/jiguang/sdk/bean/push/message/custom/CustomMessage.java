package cn.jiguang.sdk.bean.push.message.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomMessage {
    /**
     * 消息内容本身。
     */
    @JsonProperty("msg_content")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String content;

    /**
     * 消息标题。
     */
    @JsonProperty("title")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String title;

    /**
     * 消息内容类型，开发者可根据自身业务定义具体类型。
     */
    @JsonProperty("content_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String contentType;

    /**
     * 这里的Object，可以是基础数据类型
     * JSON 格式的可选参数
     */
    @JsonProperty("extras")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Map<String, Object> extras;
}
