package cn.jiguang.sdk.bean.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulePushPageResult {

    @JsonProperty("total_count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalCount;

    @JsonProperty("total_pages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;

    @JsonProperty("page")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    @JsonProperty("schedules")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SchedulePushParam> schedules;

}
