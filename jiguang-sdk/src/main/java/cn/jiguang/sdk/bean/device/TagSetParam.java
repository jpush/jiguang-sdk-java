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
        /**
         * 最多各支持 1000 个.
         */
        @JsonProperty("add")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> add;

        /**
         * 最多各支持 1000 个.
         */
        @JsonProperty("remove")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> remove;
    }

}
