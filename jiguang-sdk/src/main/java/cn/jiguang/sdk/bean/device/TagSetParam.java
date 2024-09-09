package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TagSetParam {

    @JsonProperty("registration_ids")
    private RegistrationIds registrationIds;

    @Data
    public static class RegistrationIds {
        @JsonProperty("add")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> add;

        @JsonProperty("remove")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> remove;
    }

}
