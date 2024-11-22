package cn.jiguang.sdk.bean.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeviceClearParam {

    /**
     * 空字符串代表清除，具体参考
     */
    @JsonProperty("tags")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tags;

    @JsonProperty("alias")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String alias;

    @JsonProperty("mobile")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mobile;

    /**
     * @param tags   是否清除 tags
     * @param alias  是否清除 alias
     * @param mobile 是否清除 mobile
     */
    public static DeviceClearParam of(boolean tags, boolean alias, boolean mobile) {
        DeviceClearParam param = new DeviceClearParam();
        param.setTags(tags ? "" : null);
        param.setAlias(alias ? "" : null);
        param.setMobile(mobile ? "" : null);
        return param;
    }

    /**
     * @param tags  是否清除 tags
     * @param alias 是否清除 alias
     */
    public static DeviceClearParam of(boolean tags, boolean alias) {
        DeviceClearParam param = new DeviceClearParam();
        param.setTags(tags ? "" : null);
        param.setAlias(alias ? "" : null);
        param.setMobile(null);
        return param;
    }

    /**
     * @param tags 是否清除 tags
     */
    public static DeviceClearParam of(boolean tags) {
        DeviceClearParam param = new DeviceClearParam();
        param.setTags(tags ? "" : null);
        param.setMobile(null);
        return param;
    }

}
