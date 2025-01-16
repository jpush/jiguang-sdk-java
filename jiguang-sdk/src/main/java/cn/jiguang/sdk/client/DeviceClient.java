package cn.jiguang.sdk.client;

import cn.jiguang.sdk.bean.device.*;
import cn.jiguang.sdk.enums.platform.Platform;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface DeviceClient {
    /**
     * 获取当前设备的所有属性，包含 tags, alias，手机号码 mobile。
     */
    @RequestLine("GET /v3/devices/{registration_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    DeviceGetResult getDevice(@Param("registration_id") String registrationId);

    /**
     * 更新当前设备的指定属性，当前支持 tags, alias，手机号码 mobile。
     */
    @RequestLine("POST /v3/devices/{registration_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void setDevice(@Param("registration_id") String registrationId, DeviceSetParam param);

    /**
     * 获取指定 alias 下的设备，最多输出 10 个，超过 10 个默认输出 10 个。
     */
    @RequestLine("GET /v3/aliases/{alias}?platform={platform}")
    @Headers("Content-Type: application/json; charset=utf-8")
    AliasGetResult getAlias(@Param("alias") String alias, @Param("platform") String platform);

    /**
     * 删除一个别名，以及该别名与设备的绑定关系。
     */
    @RequestLine("DELETE /v3/aliases/{alias}?platform={platform}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void deleteAlias(@Param("alias") String alias, @Param("platform") String platform);

    /**
     * 获取当前应用的所有标签列表。
     */
    @RequestLine("GET /v3/tags")
    @Headers("Content-Type: application/json; charset=utf-8")
    TagsGetResult getTags();

    /**
     * 查询某个设备是否在 tag 下。
     */
    @RequestLine("GET /v3/tags/{tag}/registration_ids/{registration_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    TagGetResult getTag(@Param("tag") String tag, @Param("registration_id") String registrationId);

    /**
     * 为一个标签添加或者删除设备。
     */
    @RequestLine("POST /v3/tags/{tag}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void setTag(@Param("tag") String tag, TagSetParam param);

    /**
     * 删除一个标签，以及标签与设备之间的关联关系。
     */
    @RequestLine("DELETE /v3/tags/{tag}?platform={platform}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void deleteTag(@Param("tag") String tag, @Param("platform") String platform);

    /**
     * 获取用户在线状态
     */
    @RequestLine("POST /v3/devices/status")
    @Headers("Content-Type: application/json; charset=utf-8")
    List<DeviceStatusGetResult> getDeviceStatus(@Param("registration_ids") List<String> registrationIds);

    /**
     * 注：Web 平台的推送属于定制功能；
     * web 平台分为正常用户和临时用户。此接口为覆盖逻辑，直接使用输入的标签别名覆盖数据库中已有数据。正常用户还可以使用 deviceAPI 中的其他操作来操作别名标签；临时用户不可以。
     * 更新当前 Web 账号的指定属性，当前支持 tags, alias。
     */
    @RequestLine("POST /v3/webdevices/{registration_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void setWebDevice(@Param("registration_id") String registrationId,WebDeviceSetParam param);
}
