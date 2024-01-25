package cn.jiguang.sdk.bean.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppCreateParam {

    @JsonProperty("app_name")
    private String appName;

    @JsonProperty("android_package")
    private String packageName;

    @JsonProperty("group_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String groupName;
}
