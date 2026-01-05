package cn.jiguang.sdk.bean.device;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagsGetResult extends BaseResult {

    @JsonProperty("tags")
    private List<String> tags;

}
