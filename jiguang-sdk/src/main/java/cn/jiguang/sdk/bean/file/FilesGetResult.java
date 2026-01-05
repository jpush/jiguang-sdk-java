package cn.jiguang.sdk.bean.file;

import cn.jiguang.sdk.bean.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FilesGetResult extends BaseResult {

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("files")
    private List<FileGetResult> files;

}
