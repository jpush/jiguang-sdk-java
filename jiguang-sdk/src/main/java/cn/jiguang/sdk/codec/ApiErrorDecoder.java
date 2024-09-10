package cn.jiguang.sdk.codec;

import cn.jiguang.sdk.bean.push.other.TemplateResult;
import cn.jiguang.sdk.exception.ApiErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ApiErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        Response.Body body = response.body();
        if (methodKey.contains("templateSend") || methodKey.contains("scheduleTemplateSend")) {
            try {
                String bodyContent = Util.toString(body.asReader(StandardCharsets.UTF_8));
                TemplateResult templateResult = new ObjectMapper().readValue(bodyContent, TemplateResult.class);
                return buildApiErrorException(status, templateResult.getCode(), templateResult.getMessage());
            } catch (Exception exception) {
                log.error("unknown error", exception);
                return buildApiErrorException(status, 500, "unknown error");
            }
        }
        try {
            String bodyContent = Util.toString(body.asReader(StandardCharsets.UTF_8));
            ApiErrorException.ApiError apiError = new ObjectMapper().readValue(bodyContent, ApiErrorException.ApiError.class);
            return new ApiErrorException(status, apiError);
        } catch (Exception exception) {
            log.error("unknown error", exception);
            return buildApiErrorException(status, 500, "unknown error");
        }
    }

    private ApiErrorException buildApiErrorException(int status, int code, String message) {
        ApiErrorException.ApiError.Error error = new ApiErrorException.ApiError.Error();
        error.setCode(code);
        error.setMessage(message);
        ApiErrorException.ApiError apiError = new ApiErrorException.ApiError();
        apiError.setError(error);
        return new ApiErrorException(status, apiError);
    }

}
