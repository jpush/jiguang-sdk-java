package cn.jiguang.sdk.codec;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public class ApiDecoder implements Decoder {

    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String JSON_CONTENT_TYPE = "application/json";
    
    private final JacksonDecoder jacksonDecoder;

    public ApiDecoder() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        this.jacksonDecoder = new JacksonDecoder(objectMapper);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (!isJsonResponse(response)) {
            return null;
        }
        
        // 解码响应体
        Object result = jacksonDecoder.decode(response, type);
        
        // 如果结果是BaseResult的子类，填充响应头信息
        fillResponseHeaders(result, response.headers());
        
        return result;
    }

    /**
     * 判断是否为JSON响应
     */
    private boolean isJsonResponse(Response response) {
        String contentType = getHeaderValue(response.headers(), CONTENT_TYPE_HEADER);
        return contentType != null && contentType.startsWith(JSON_CONTENT_TYPE);
    }

    /**
     * 填充响应头信息
     */
    private void fillResponseHeaders(Object result, Map<String, Collection<String>> headers) {
        if (result instanceof BaseResult) {
            ((BaseResult) result).setResponseHeaders(headers);
        }
    }

    /**
     * 获取响应头值
     */
    private String getHeaderValue(Map<String, Collection<String>> headers, String headerName) {
        Collection<String> values = headers.get(headerName);
        return values != null && !values.isEmpty() ? values.iterator().next() : null;
    }

}
