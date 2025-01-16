package cn.jiguang.sdk.bean.schedule;

import cn.jiguang.sdk.bean.push.PushParam;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulePushParam {

    /**
     * 和 push api 中 cid 用法一致，详见 cid 说明 。注：schedule api payload 中的 push 字段中含有 cid 字段将会被忽略。
     */
    @JsonProperty("cid")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("trigger")
    private Trigger trigger;

    @JsonProperty("push")
    private PushParam pushParam;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Trigger {
        /**
         * 表示定时任务
         */
        @JsonProperty("single")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Single single;

        /**
         * 表示定期任务
         */
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

            /**
             * frequency 此项与 time_unit 的乘积共同表示的定期任务的执行周期，frequency 目前支持的最大值为 100 。如 time_unit 为 day，frequency 为 2，则表示每两天触发一次推送，首次执行是间隔起始日期 1 天后的第一个执行时间点。
             * 如 time_unit 为 week，frequency 为 2，则表示每两周触发一次推送，首次执行是间隔起始日期 7 天后的第一个执行时间点。
             */
            @JsonProperty("frequency")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Integer frequency;

            /**
             * time_unit 表示定期任务的执行的最小时间单位有："day"、"week" 及 "month" 三种。
             * 大小写不敏感，如 "day", "Day", "DAy" 均为合法的 time_unit。
             */
            @JsonProperty("time_unit")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private TimeUnit timeUnit;

            /**
             * point 一个列表，此项与 time_unit 相对应
             * 当 time_unit 为 day 时 point 此项无效
             * 当 time_unit 为 week 时，point 为对应项的一项或多项，表示星期几进行触发，point 中的值大小写不敏感。取值："MON","TUE","WED","THU","FRI","SAT","SUN"
             * 当 time_unit 为 month 时，point 为当前进行月对应的日期，且必须有效，如 month 为 2 月，则 31 或 30 日是不会进行触发的。取值："01","02","03" .... "31"
             */
            @JsonProperty("point")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private List<String> point;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Single {
            /**
             * time 为必选项且格式为 "yyyy-MM-dd HH:mm:ss"
             * 如 "2014-02-15 13:16:59"，不能为 "2014-2-15 13:16:59" 或 "2014-12-15 13:16"，即日期时间格式必须完整。定时任务的最晚时间不能超过一年。
             */
            @JsonProperty("time")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            private LocalDateTime time;
        }
    }

}
