package cn.jiguang.sdk.bean.geofence;

import cn.jiguang.sdk.enums.geofence.GeofenceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GeofenceGetResult {

    @JsonProperty("geofenceid")
    private String geofenceId;

    /**
     * 地理围栏名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 圆心的经纬度信息
     */
    @JsonProperty("center")
    private Center center;

    /**
     * 圆形半径， 单位：米 ，校验半径为大于 0 的整数
     */
    @JsonProperty("radius")
    private Integer radius;

    /**
     * 过度状态，进入或离开该区域
     * in： 进入
     * out： 离开
     */
    @JsonProperty("status")
    private GeofenceStatus status;

    /**
     * 地理围栏类型，默认值需要传 1
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 地理围栏的结束时间，最长是一年后
     */
    @JsonProperty("expiration")
    private Long expiration;

    @JsonProperty("platform")
    private Object platform;

    @JsonProperty("audience")
    private Object audience;

    @JsonProperty("time_to_live")
    private Long timeToLive;

    @JsonProperty("create_time")
    private Long createTime;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Center {
        /**
         * 经度，wgs84 坐标系，必填
         */
        @JsonProperty("lon")
        private String longitude;

        /**
         * 维度，wgs84 坐标系，必填
         */
        @JsonProperty("lat")
        private String latitude;
    }
}
