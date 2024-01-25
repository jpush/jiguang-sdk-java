package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TagGetResult {

    @JsonProperty("result")
    private Boolean result;

}
