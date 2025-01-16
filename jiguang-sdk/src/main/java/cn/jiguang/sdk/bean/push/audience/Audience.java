package cn.jiguang.sdk.bean.push.audience;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Audience {
    @JsonProperty("registration_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> registrationIdList;

    @JsonProperty("tag")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tagOrList;
    @JsonProperty("tag_and")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tagAndList;
    @JsonProperty("tag_not")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tagNotList;

    @JsonProperty("alias")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> aliasList;
}
