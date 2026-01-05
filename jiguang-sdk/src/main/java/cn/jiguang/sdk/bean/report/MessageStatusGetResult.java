package cn.jiguang.sdk.bean.report;

import cn.jiguang.sdk.bean.BaseResult;
import cn.jiguang.sdk.constants.ApiConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageStatusGetResult extends BaseResult {

    /**
     * 值参考{@link ApiConstants}
     */
    @JsonProperty("status")
    private Integer status;

}
