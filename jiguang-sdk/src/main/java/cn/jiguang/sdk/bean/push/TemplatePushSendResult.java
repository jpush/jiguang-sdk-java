package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.push.other.TemplateResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TemplatePushSendResult {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Data data;

    @lombok.Data
    public static class Data {

        @JsonProperty("push_list")
        private List<TemplateResult> results;

    }

}
