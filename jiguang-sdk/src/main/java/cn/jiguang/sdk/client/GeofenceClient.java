package cn.jiguang.sdk.client;

import cn.jiguang.sdk.bean.geofence.*;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface GeofenceClient {
    /**
     * 创建地理围栏
     */
    @RequestLine("POST /v3/geofence")
    @Headers("Content-Type: application/json; charset=utf-8")
    GeofenceCreateResult create(GeofenceCreateParam param);

    /**
     * 修改地理围栏
     */
    @RequestLine("PUT /v3/geofence/{geofence_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void update(@Param("geofence_id") String geofenceId, GeofenceCreateParam param);

    /**
     * 删除地理围栏
     */
    @RequestLine("DELETE /v3/geofence/{geofence_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void delete(@Param("geofence_id") String geofenceId);

    /**
     * 获取地理围栏
     */
    @RequestLine("GET /v3/geofence/{geofence_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    GeofenceGetResult get(@Param("geofence_id") String geofenceId);

    /**
     * 分页获取地理围栏
     */
    @RequestLine("GET /v3/geofences/detail?page={page}&size={size}&qname={qname}")
    @Headers("Content-Type: application/json; charset=utf-8")
    GeofencePageResult page(@Param("page") Integer page, @Param("size") Integer size, @Param("qname") String name);

    /**
     * 获取地理围栏
     */
    @RequestLine("GET /v3/geofences/outline}")
    @Headers("Content-Type: application/json; charset=utf-8")
    GeofenceGetOutlineResult getOutlines();
}
