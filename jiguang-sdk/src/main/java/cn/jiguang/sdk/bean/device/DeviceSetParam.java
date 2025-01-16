package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeviceSetParam {

    /**
     * 支持 add, remove 或者空字符串。当 tags 参数为空字符串的时候，表示清空所有的 tags；add/remove 下是增加或删除指定的 tag；
     */
    @JsonProperty("tags")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Tags tags;

    /**
     * 更新设备的别名属性；当别名为空串时，删除指定设备的别名；
     */
    @JsonProperty("alias")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String alias;

    /**
     * 设备关联的手机号码；当 mobile 为空串时，表示清空设备关联的手机号码。
     */
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
