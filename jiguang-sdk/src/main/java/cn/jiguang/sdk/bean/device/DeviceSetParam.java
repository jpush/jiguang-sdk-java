package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeviceSetParam {

    @JsonProperty("tags")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Tags tags;

    @JsonProperty("alias")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String alias;

    @JsonProperty("mobile")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mobile;

    @Data
    public static class Tags {
        @JsonProperty("add")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> add;

        @JsonProperty("remove")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> remove;
    }

}
