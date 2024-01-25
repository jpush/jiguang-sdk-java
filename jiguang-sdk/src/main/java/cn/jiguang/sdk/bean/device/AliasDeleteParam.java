package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AliasDeleteParam {
    @JsonProperty("registration_ids")
    private RegistrationIds registrationIds;

    @Data
    public static class RegistrationIds {
        @JsonProperty("remove")
        private List<String> remove;
    }

}
