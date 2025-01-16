package cn.jiguang.sdk.bean.geofence;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GeofenceGetOutlineResult {
    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("geofence")
    private List<Outline> outlines;

    @Data
    public static class Outline {
        /**
         * 地理围栏名称
         */
        @JsonProperty("name")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String name;

        @JsonProperty("geofenceid")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String geofenceId;
    }

}
