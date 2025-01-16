package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WebDeviceSetParam {
    /**
     * 用户标示，true 为临时用户，false 为正常用户
     */
    @JsonProperty("temporary")
    private Boolean temporary;

    /**
     * 覆盖逻辑，一个账号可设置多个标签；
     */
    private List<String> tags;

    /**
     * 覆盖逻辑，一个账号仅允许设置一个别名。
     */
    private String alias;
}
