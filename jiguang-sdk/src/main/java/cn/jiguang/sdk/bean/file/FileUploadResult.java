package cn.jiguang.sdk.bean.file;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileUploadResult extends BaseResult {

    @JsonProperty("file_id")
    private String fileId;

}
