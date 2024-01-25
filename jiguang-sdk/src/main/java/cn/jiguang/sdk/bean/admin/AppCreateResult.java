package cn.jiguang.sdk.bean.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppCreateResult {

    @JsonProperty("app_key")
    private String appKey;

    @JsonProperty("android_package")
    private String packageName;

    @JsonProperty("is_new_created")
    private Boolean isNewCreated;

}
