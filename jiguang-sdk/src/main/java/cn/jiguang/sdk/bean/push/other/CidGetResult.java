package cn.jiguang.sdk.bean.push.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CidGetResult {

    @JsonProperty("cidlist")
    private List<String> cidList;

}
