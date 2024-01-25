package cn.jiguang.sdk.bean.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FilesGetResult {

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("files")
    private List<FileGetResult> files;

}
