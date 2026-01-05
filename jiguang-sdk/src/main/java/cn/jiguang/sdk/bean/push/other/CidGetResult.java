package cn.jiguang.sdk.bean.push.other;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CidGetResult extends BaseResult {

    @JsonProperty("cidlist")
    private List<String> cidList;

}
