package cn.jiguang.sdk.client;

import cn.jiguang.sdk.bean.device.*;
import cn.jiguang.sdk.enums.platform.Platform;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * (<a href="https://docs.jiguang.cn/jpush/server/push/rest_api_v3_device">REST API - Device</a>)
 */
public interface DeviceClient {

    @RequestLine("GET /v3/devices/{registration_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    DeviceGetResult getDevice(@Param("registration_id") String registrationId);

    @RequestLine("POST /v3/devices/{registration_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void setDevice(@Param("registration_id") String registrationId, DeviceSetParam param);

    @RequestLine("POST /v3/devices/{registration_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void clearDevice(@Param("registration_id") String registrationId, DeviceClearParam param);

    @RequestLine("GET /v3/aliases/{alias}?platform={platform}&new_format=true")
    @Headers("Content-Type: application/json; charset=utf-8")
    AliasGetResult getAlias(@Param("alias") String alias, @Param("platform") Platform platform);

    @RequestLine("DELETE /v3/aliases/{alias}?platform={platform}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void deleteAlias(@Param("alias") String alias, @Param("platform") Platform platform);

    @RequestLine("POST /v3/aliases/{alias}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void deleteAliases(@Param("alias") String alias, AliasDeleteParam param);

    @RequestLine("GET /v3/tags")
    @Headers("Content-Type: application/json; charset=utf-8")
    TagsGetResult getTags();

    @RequestLine("GET /v3/tags/{tag}/registration_ids/{registration_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    TagGetResult getTag(@Param("tag") String tag, @Param("registration_id") String registrationId);

    @RequestLine("POST /v3/tags/{tag}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void setTag(@Param("tag") String tag, TagSetParam param);

    @RequestLine("DELETE /v3/tags/{tag}?platform={platform}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void deleteTag(@Param("tag") String tag, @Param("platform") Platform platform);

    @RequestLine("POST /v3/devices/status")
    @Headers("Content-Type: application/json; charset=utf-8")
    DeviceStatusGetResult getDeviceStatus(@Param("registration_ids") List<String> registrationIds);

}
