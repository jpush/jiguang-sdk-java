package cn.jiguang.sdk.bean.geofence;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeofencePageParam {
    @JsonProperty("page")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    @JsonProperty("size")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;

    @JsonProperty("qname")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
}
