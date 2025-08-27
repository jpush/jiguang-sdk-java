package cn.jiguang.sdk.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiErrorException extends RuntimeException {

    private final int stats;
    private final ApiError apiError;

    public ApiErrorException(int status, ApiError apiError) {
        super(apiError.getError().getMessage());
        this.stats = status;
        this.apiError = apiError;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApiError implements Serializable {
        @JsonProperty("msg_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String msgId;

        @JsonProperty("error")
        private Error error;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Error implements Serializable {
            @JsonProperty("code")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Integer code;

            @JsonProperty("message")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String message;

            /*@JsonProperty("illegal_rids")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private List<String> illegalRids;*/
        }
    }
}
