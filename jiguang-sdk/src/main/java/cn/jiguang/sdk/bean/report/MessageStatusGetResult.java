package cn.jiguang.sdk.bean.report;

import cn.jiguang.sdk.constants.ApiConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageStatusGetResult {

    /**
     * 值参考{@link ApiConstants}
     */
    @JsonProperty("status")
    private Integer status;

}
