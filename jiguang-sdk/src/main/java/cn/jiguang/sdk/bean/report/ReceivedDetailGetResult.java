package cn.jiguang.sdk.bean.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivedDetailGetResult {
    @JsonProperty("msg_id")
    private String msgId;

    @JsonProperty("android_received")
    private Long androidReceived;

    @JsonProperty("ios_apns_sent")
    private Long iosApnsSent;

    @JsonProperty("ios_apns_received")
    private Long iosApnsReceived;

    @JsonProperty("ios_msg_received")
    private Long iosMsgReceived;

    @JsonProperty("hmos_hmpns_sent")
    private Long hmosHmpnsSent;

    @JsonProperty("hmos_hmpns_received")
    private Long hmosHmpnsReceived;

    @JsonProperty("hmos_msg_sent")
    private Long hmosMsgSent;

    @JsonProperty("hmos_msg_received")
    private Long hmosMsgReceived;

    @JsonProperty("web_msg_received")
    private Long webMsgReceived;
}
