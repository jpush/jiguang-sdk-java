package cn.jiguang.sdk.bean.report;

import cn.jiguang.sdk.enums.timeunit.TimeUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailGetResult {

    @JsonProperty("time_unit")
    private TimeUnit timeUnit;

    @JsonProperty("start")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("items")
    private List<Item> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        @JsonProperty("time")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;

        @JsonProperty("android")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Detail android;

        @JsonProperty("ios")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Detail iOS;

        @JsonProperty("quickapp")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Detail quickApp;

        @JsonProperty("hmos")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Detail HMOS;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Detail {
            @JsonProperty("new")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Long news;

            @JsonProperty("online")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Long online;

            @JsonProperty("active")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Long active;
        }
    }

}
