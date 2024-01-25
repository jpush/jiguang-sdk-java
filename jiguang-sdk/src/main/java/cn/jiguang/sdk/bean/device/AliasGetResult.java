package cn.jiguang.sdk.bean.device;

import cn.jiguang.sdk.enums.platform.Platform;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AliasGetResult {

    @JsonProperty("data")
    private List<Data> data;

    @lombok.Data
    public static class Data {
        @JsonProperty("registration_id")
        private String registrationId;

        @JsonProperty("platform")
        private Platform platform;

        @JsonProperty("last_online_date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate lastOnlineDate;
    }

}
