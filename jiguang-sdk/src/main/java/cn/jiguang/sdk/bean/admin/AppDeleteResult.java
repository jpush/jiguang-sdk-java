package cn.jiguang.sdk.bean.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppDeleteResult {

    @JsonProperty("success")
    private String success;

}
