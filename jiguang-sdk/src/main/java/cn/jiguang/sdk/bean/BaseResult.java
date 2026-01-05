package cn.jiguang.sdk.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Collection;
import java.util.Map;

/**
 * 所有Result的基类，包含HTTP响应头信息
 */
@Data
public class BaseResult {

    /**
     * 限流信息
     */
    @JsonIgnore
    private RateLimit rateLimit;

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

}

