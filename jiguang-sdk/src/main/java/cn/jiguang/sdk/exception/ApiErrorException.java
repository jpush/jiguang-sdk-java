package cn.jiguang.sdk.exception;

import cn.jiguang.sdk.bean.RateLimit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiErrorException extends RuntimeException {

    private final int stats;
    private final ApiError apiError;
    
    /**
     * 限流信息
     */
    private RateLimit rateLimit;

    public ApiErrorException(int status, ApiError apiError) {
        super(apiError.getError().getMessage());
        this.stats = status;
        this.apiError = apiError;
    }

    /**
     * 从响应头中提取并设置相关字段
     */
    public void setResponseHeaders(Map<String, Collection<String>> headers) {
        if (headers == null) {
            return;
        }

        // 提取限流信息
        RateLimit rateLimit = new RateLimit();
        rateLimit.setLimit(extractIntHeader(headers, "x-rate-limit-limit"));
        rateLimit.setRemaining(extractIntHeader(headers, "x-rate-limit-remaining"));
        rateLimit.setReset(extractIntHeader(headers, "x-rate-limit-reset"));
        this.rateLimit = rateLimit;
    }

    /**
     * 提取字符串类型的响应头
     */
    private String extractStringHeader(Map<String, Collection<String>> headers, String headerName) {
        Collection<String> values = headers.get(headerName);
        if (values != null && !values.isEmpty()) {
            return values.iterator().next();
        }
        return null;
    }

    /**
     * 提取整数类型的响应头
     */
    private Integer extractIntHeader(Map<String, Collection<String>> headers, String headerName) {
        String value = extractStringHeader(headers, headerName);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
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
