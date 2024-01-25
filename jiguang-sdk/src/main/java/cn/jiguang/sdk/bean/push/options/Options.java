package cn.jiguang.sdk.bean.push.options;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Options {

    @JsonProperty("sendno")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long sendNo;

    @JsonProperty("override_msg_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long overrideMsgId;

    @JsonProperty("apns_production")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean apnsProduction;

    @JsonProperty("time_to_live")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long timeToLive;

    @JsonProperty("alternate_set")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean alternateEnable;

    @JsonProperty("portal_extra")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PortalExtra portalExtra;

    @JsonProperty("target_event")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> targetEvent;

    @JsonProperty("third_party_channel")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode thirdPartyChannel;

    @JsonProperty("apns_collapse_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String apnsCollapseId;

    @JsonProperty("big_push_duration")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer bigPushDuration;

    @JsonProperty("classification")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer classification;

    @JsonProperty("geofence")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode geofence;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PortalExtra {
        @JsonProperty("task")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String task;
    }

}
