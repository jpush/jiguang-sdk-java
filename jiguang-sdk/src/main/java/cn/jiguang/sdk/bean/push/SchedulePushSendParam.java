package cn.jiguang.sdk.bean.push;

import cn.jiguang.sdk.enums.timeunit.TimeUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class SchedulePushSendParam {

    @JsonProperty("name")
    private String name;

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("trigger")
    private Trigger trigger;

    @JsonProperty("push")
    private PushSendParam pushSendParam;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Trigger {
        @JsonProperty("single")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Single single;

        @JsonProperty("periodical")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Periodical periodical;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Periodical {
            @JsonProperty("start")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            private LocalDateTime startTime;

            @JsonProperty("end")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            private LocalDateTime endTime;

            @JsonProperty("time")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonFormat(pattern = "HH:mm:ss")
            private LocalTime time;

            @JsonProperty("frequency")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Integer frequency;

            @JsonProperty("time_unit")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private TimeUnit timeUnit;

            @JsonProperty("point")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private List<String> point;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Single {
            @JsonProperty("time")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            private LocalDateTime time;
        }
    }

}
