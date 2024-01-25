package cn.jiguang.sdk.codec;

import cn.jiguang.sdk.exception.ApiErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.nio.charset.StandardCharsets;

public class ApiErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        try {
            Response.Body body = response.body();
            String bodyContent = Util.toString(body.asReader(StandardCharsets.UTF_8));
            ApiErrorException.ApiError apiError = new ObjectMapper().readValue(bodyContent, ApiErrorException.ApiError.class);
            return new ApiErrorException(status, apiError);
        } catch (Exception exception) {
            return new ApiErrorException(status, null);
        }
    }

}
