package cn.jiguang.sdk.bean.device;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagGetResult extends BaseResult {

    @JsonProperty("result")
    private Boolean result;

}
