package cn.jiguang.sdk.codec;

import cn.jiguang.sdk.bean.push.other.TemplateResult;
import cn.jiguang.sdk.exception.ApiErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class ApiErrorDecoder implements ErrorDecoder {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        
        ApiErrorException exception = isTemplateMethod(methodKey) 
            ? decodeTemplateError(status, response.body())
            : decodeStandardError(status, response.body());
        
        // 填充响应头信息
        fillResponseHeaders(exception, response.headers());
        
        return exception;
    }

    /**
     * 判断是否为模板方法
     */
    private boolean isTemplateMethod(String methodKey) {
        return methodKey.contains("templateSend") || methodKey.contains("scheduleTemplateSend");
    }

    /**
     * 解码模板错误
     */
    private ApiErrorException decodeTemplateError(int status, Response.Body body) {
        try {
            String bodyContent = readBody(body);
            TemplateResult templateResult = OBJECT_MAPPER.readValue(bodyContent, TemplateResult.class);
            return buildApiErrorException(status, templateResult.getCode(), templateResult.getMessage());
        } catch (Exception e) {
            log.error("Failed to decode template error", e);
            return buildUnknownError(status);
        }
    }

    /**
     * 解码标准错误
     */
    private ApiErrorException decodeStandardError(int status, Response.Body body) {
        try {
            String bodyContent = readBody(body);
            ApiErrorException.ApiError apiError = OBJECT_MAPPER.readValue(bodyContent, ApiErrorException.ApiError.class);
            return new ApiErrorException(status, apiError);
        } catch (Exception e) {
            log.error("Failed to decode standard error", e);
            return buildUnknownError(status);
        }
    }

    /**
     * 填充响应头信息
     */
    private void fillResponseHeaders(ApiErrorException exception, Map<String, Collection<String>> headers) {
        exception.setResponseHeaders(headers);
    }

    /**
     * 读取响应体内容
     */
    private String readBody(Response.Body body) throws IOException {
        return Util.toString(body.asReader(StandardCharsets.UTF_8));
    }

    /**
     * 构建未知错误
     */
    private ApiErrorException buildUnknownError(int status) {
        return buildApiErrorException(status, 500, "unknown error");
    }

    /**
     * 构建API错误异常
     */
    private ApiErrorException buildApiErrorException(int status, int code, String message) {
        ApiErrorException.ApiError.Error error = new ApiErrorException.ApiError.Error();
        error.setCode(code);
        error.setMessage(message);
        
        ApiErrorException.ApiError apiError = new ApiErrorException.ApiError();
        apiError.setError(error);
        
        return new ApiErrorException(status, apiError);
    }

}
