package cn.jiguang.sdk.bean.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageUrlAddParam {

    @JsonProperty("image_type")
    private Integer imageType;

    @JsonProperty("image_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imageUrl;

    @JsonProperty("jiguang_image_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String jiguangImageUrl;

    @JsonProperty("xiaomi_image_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String xiaomiImageUrl;

    @JsonProperty("huawei_image_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String huaweiImageUrl;

    @JsonProperty("honor_image_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String honorImageUrl;

    @JsonProperty("oppo_image_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String oppoImageUrl;

    @JsonProperty("fcm_image_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fcmImageUrl;

    @JsonProperty("hmos_image_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hmosImageUrl;

}
