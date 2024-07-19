package cn.jiguang.sdk.bean.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivedDetailGetResult {

    @JsonProperty("msg_id")
    private String msgId;

    @JsonProperty("jpush_received")
    private Long jpushReceived;

    @JsonProperty("android_pns_sent")
    private Long androidPnsSent;

    @JsonProperty("android_pns_received")
    private Long androidPnsReceived;

    @JsonProperty("ios_apns_sent")
    private Long iosApnsSent;

    @JsonProperty("ios_apns_received")
    private Long iosApnsReceived;

    @JsonProperty("ios_msg_received")
    private Long iosMsgReceived;

    @JsonProperty("live_acivity_sent")
    private Long liveAcivitySent;

    @JsonProperty("live_acivity_received")
    private Long liveAcivityReceived;

    @JsonProperty("quickapp_jpush_received")
    private Long quickappJpushReceived;

    @JsonProperty("quickapp_pns_sent")
    private Long quickappPnsSent;

    @JsonProperty("hmos_hmpns_received")
    private Long hmosHmpnsReceived;

    @JsonProperty("hmos_hmpns_sent")
    private Long hmosHmpnsSent;

    @JsonProperty("hmos_msg_received")
    private Long hmosMsgReceived;

}
