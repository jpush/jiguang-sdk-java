package cn.jiguang.sdk.bean.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import feign.form.FormProperty;
import lombok.Data;

import java.io.File;

@Data
public class ImageFileAddParam {

    @FormProperty("image_type")
    private Integer imageType;

    @FormProperty("xiaomi_file")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private File xiaomiImageFile;

    @FormProperty("oppo_file")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private File oppoImageFile;

}
