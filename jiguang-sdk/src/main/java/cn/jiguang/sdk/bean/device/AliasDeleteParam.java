package cn.jiguang.sdk.bean.device;

import cn.jiguang.sdk.enums.platform.Platform;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AliasDeleteParam {
    private String alias;

    private List<Platform> platforms;
}
