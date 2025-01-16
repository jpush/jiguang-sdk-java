package cn.jiguang.sdk.bean.geofence;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeofenceGetParam {

    @JsonProperty("geofenceid")
    private String geofenceId;

}
