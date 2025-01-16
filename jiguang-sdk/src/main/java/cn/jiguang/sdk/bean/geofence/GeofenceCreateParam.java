package cn.jiguang.sdk.bean.geofence;

import cn.jiguang.sdk.enums.geofence.GeofenceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class GeofenceCreateParam {
    /**
     * 地理围栏名称
     */
    private String name;

    /**
     * 圆心的经纬度信息
     */
    private Center center;

    /**
     * 圆形半径， 单位：米 ，校验半径为大于 0 的整数
     */
    private Integer radius;

    /**
     * 过度状态，进入或离开该区域
     * in： 进入
     * out： 离开
     */
    private GeofenceStatus status;

    /**
     * 是否重复提示
     */
    private Boolean repeat;

    /**
     * 地理围栏类型，默认值需要传 1
     */
    private Integer type;

    /**
     * 地理围栏的结束时间，最长是一年后
     */
    private Long expiration;

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

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
