package cn.jiguang.sdk.codec;

import cn.jiguang.sdk.bean.push.PushSendParam;
import cn.jiguang.sdk.bean.push.other.SM2Push;
import cn.jiguang.sdk.utils.SM2Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.jackson.JacksonEncoder;
import lombok.SneakyThrows;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

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
        Map<String, Collection<String>> headers = template.headers();
        if (headers.containsKey("X-Encrypt-Type")) {
            String encryptType = headers.get("X-Encrypt-Type").stream().findFirst().orElse("");
            if (encryptType.startsWith("SM2") && object instanceof PushSendParam) {
                SM2Push sm2Push = convertToSM2Push((PushSendParam) object);
                jacksonEncoder.encode(sm2Push, SM2Push.class, template);
                return;
            }
        }
        if (headers.containsKey("Content-Type")) {
            String contentType = headers.get("Content-Type").stream().findFirst().orElse("");
            if (contentType.startsWith("application/json")) {
                jacksonEncoder.encode(object, bodyType, template);
            } else if (contentType.startsWith("multipart/form-data")) {
                formEncoder.encode(object, bodyType, template);
            }
        }
    }

    @SneakyThrows
    private SM2Push convertToSM2Push(PushSendParam pushSendParam) {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] encrypt = SM2Util.encrypt(objectMapper.writeValueAsString(pushSendParam));
        SM2Push sm2Push = new SM2Push();
        sm2Push.setPayload(Base64.getEncoder().encodeToString(encrypt));
        sm2Push.setAudience(pushSendParam.getAudience());
        return sm2Push;
    }

}
