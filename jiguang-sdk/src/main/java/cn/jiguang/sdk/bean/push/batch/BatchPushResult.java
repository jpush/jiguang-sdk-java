package cn.jiguang.sdk.bean.push.batch;

import cn.jiguang.sdk.exception.ApiErrorException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchPushResult {

    @JsonProperty("msg_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageId;

    @JsonProperty("error")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ApiErrorException.ApiError.Error error;

}
