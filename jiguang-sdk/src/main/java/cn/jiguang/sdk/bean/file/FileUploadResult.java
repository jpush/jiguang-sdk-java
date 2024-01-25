package cn.jiguang.sdk.bean.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FileUploadResult {

    @JsonProperty("file_id")
    private String fileId;

}
