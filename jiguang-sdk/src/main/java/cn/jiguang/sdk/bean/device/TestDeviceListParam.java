package cn.jiguang.sdk.bean.device;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class TestDeviceListParam {

    private String registrationId;

    private String deviceName;

    private Integer page;

    private Integer pageSize;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> queryMap = new LinkedHashMap<>();
        putIfNotNull(queryMap, "registration_id", registrationId);
        putIfNotNull(queryMap, "device_name", deviceName);
        putIfNotNull(queryMap, "page", page);
        putIfNotNull(queryMap, "page_size", pageSize);
        return queryMap;
    }

    private void putIfNotNull(Map<String, Object> queryMap, String key, Object value) {
        if (value != null) {
            queryMap.put(key, value);
        }
    }

}
