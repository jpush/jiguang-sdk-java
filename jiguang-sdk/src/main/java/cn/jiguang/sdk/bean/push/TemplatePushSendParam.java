package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.bean.push.other.TemplateParam;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TemplatePushSendParam {

    @JsonProperty("id")
    private String id;

    @JsonProperty("params")
    private List<TemplateParam> params;

}
