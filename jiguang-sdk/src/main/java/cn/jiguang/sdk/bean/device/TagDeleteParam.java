package cn.jiguang.sdk.bean.device;

import cn.jiguang.sdk.enums.platform.Platform;
import lombok.Data;

import java.util.List;

@Data
public class TagDeleteParam {
    private String tag;

    private List<Platform> platforms;
}
