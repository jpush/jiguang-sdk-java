package cn.jiguang.sdk.bean.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulePushDetailGetResult {

    @JsonProperty("count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer count;

    @JsonProperty("msgids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> msgIds;

}
