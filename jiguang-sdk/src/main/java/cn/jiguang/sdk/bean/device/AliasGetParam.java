package cn.jiguang.sdk.bean.device;

import cn.jiguang.sdk.enums.platform.Platform;
import lombok.Data;

import java.util.List;

@Data
public class AliasGetParam {
    private String alias;

    private List<Platform> platforms;
}
