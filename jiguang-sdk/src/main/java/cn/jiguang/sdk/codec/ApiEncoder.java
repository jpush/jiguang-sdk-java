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

    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String ENCRYPT_TYPE_HEADER = "X-Encrypt-Type";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String FORM_CONTENT_TYPE = "multipart/form-data";
    private static final String SM2_ENCRYPT_TYPE = "SM2";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    private final FormEncoder formEncoder;
    private final JacksonEncoder jacksonEncoder;

    public ApiEncoder() {
        this.formEncoder = new FormEncoder();
        this.jacksonEncoder = new JacksonEncoder(OBJECT_MAPPER);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        Map<String, Collection<String>> headers = template.headers();
        
        // 根据加密类型选择编码方式
        if (shouldEncryptWithSM2(headers, object)) {
            encodeSM2(object, template);
        } else {
            encodeByContentType(object, bodyType, template, headers);
        }
    }

    /**
     * 判断是否需要SM2加密
     */
    private boolean shouldEncryptWithSM2(Map<String, Collection<String>> headers, Object object) {
        String encryptType = getHeaderValue(headers, ENCRYPT_TYPE_HEADER);
        return object instanceof PushSendParam 
            && encryptType != null 
            && encryptType.startsWith(SM2_ENCRYPT_TYPE);
    }

    /**
     * SM2加密编码
     */
    private void encodeSM2(Object object, RequestTemplate template) {
        SM2Push sm2Push = convertToSM2Push((PushSendParam) object);
        jacksonEncoder.encode(sm2Push, SM2Push.class, template);
    }

    /**
     * 根据Content-Type编码
     */
    private void encodeByContentType(Object object, Type bodyType, RequestTemplate template, 
                                     Map<String, Collection<String>> headers) {
        String contentType = getHeaderValue(headers, CONTENT_TYPE_HEADER);
        if (contentType == null) {
            return;
        }
        
        if (contentType.startsWith(JSON_CONTENT_TYPE)) {
            jacksonEncoder.encode(object, bodyType, template);
        } else if (contentType.startsWith(FORM_CONTENT_TYPE)) {
            formEncoder.encode(object, bodyType, template);
        }
    }

    /**
     * 获取请求头值
     */
    private String getHeaderValue(Map<String, Collection<String>> headers, String headerName) {
        Collection<String> values = headers.get(headerName);
        return values != null && !values.isEmpty() ? values.iterator().next() : null;
    }

    /**
     * 转换为SM2加密对象
     */
    @SneakyThrows
    private SM2Push convertToSM2Push(PushSendParam pushSendParam) {
        byte[] encrypt = SM2Util.encrypt(OBJECT_MAPPER.writeValueAsString(pushSendParam));
        
        SM2Push sm2Push = new SM2Push();
        sm2Push.setPayload(Base64.getEncoder().encodeToString(encrypt));
        sm2Push.setAudience(pushSendParam.getAudience());
        return sm2Push;
    }

}
