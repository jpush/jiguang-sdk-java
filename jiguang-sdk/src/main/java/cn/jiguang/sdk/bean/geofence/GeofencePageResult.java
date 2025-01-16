package cn.jiguang.sdk.bean.geofence;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GeofencePageResult {
    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("geofence")
    private List<GeofenceGetResult> geofence;
}
