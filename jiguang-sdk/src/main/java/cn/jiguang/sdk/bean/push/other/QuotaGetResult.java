package cn.jiguang.sdk.bean.push.other;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuotaGetResult extends BaseResult {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Quota data;

    @Data
    public static class Quota {

        @JsonProperty("xiaomi_quota")
        private Platform xiaomi;

        @JsonProperty("oppo_quota")
        private Platform oppo;

        @JsonProperty("vivo_quota")
        private Platform vivo;

        @Data
        public static class Platform {
            @JsonProperty("system")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Detail system;

            @JsonProperty("operation")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Detail operation;
        }

        @Data
        public static class Detail {
            @JsonProperty("total")
            private Long total;

            @JsonProperty("used")
            private Long used;
        }

    }

}
