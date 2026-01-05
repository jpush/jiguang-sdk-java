package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.BaseResult;
import cn.jiguang.sdk.bean.push.other.TemplateResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplatePushSendResult extends BaseResult {

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
