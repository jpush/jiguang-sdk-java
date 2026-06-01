package cn.jiguang.sdk.bean.device;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDeviceListResult extends BaseResult {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("detail")
    private List<Detail> detail;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("page_size")
    private Integer pageSize;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Detail {

        @JsonProperty("device_name")
        private String deviceName;

        @JsonProperty("device_model")
        private String deviceModel;

        @JsonProperty("registration_id")
        private String registrationId;

        @JsonProperty("registration_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime registrationTime;

        @JsonProperty("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;

    }

}
