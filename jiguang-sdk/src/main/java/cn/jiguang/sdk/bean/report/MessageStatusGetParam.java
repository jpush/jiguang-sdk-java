package cn.jiguang.sdk.bean.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MessageStatusGetParam {

    @JsonProperty("msg_id")
    private Long messageId;

    @JsonProperty("registration_ids")
    private List<String> registrationIds;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate date;

}
