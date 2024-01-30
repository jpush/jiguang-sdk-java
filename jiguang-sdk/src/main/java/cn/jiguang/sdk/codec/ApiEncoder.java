package cn.jiguang.sdk.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.jackson.JacksonEncoder;

import java.lang.reflect.Type;

public class ApiEncoder implements Encoder {

    private final FormEncoder formEncoder;
    private final JacksonEncoder jacksonEncoder;

    public ApiEncoder() {
        formEncoder = new FormEncoder();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        jacksonEncoder = new JacksonEncoder(objectMapper);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        String contentType = template.headers().get("Content-Type").stream().findFirst().orElse(null);
        if (contentType == null) {
            return;
        }
        if (contentType.startsWith("multipart/form-data")) {
            formEncoder.encode(object, bodyType, template);
            return;
        }
        if (contentType.startsWith("application/json")) {
            jacksonEncoder.encode(object, bodyType, template);
        }
    }

}
