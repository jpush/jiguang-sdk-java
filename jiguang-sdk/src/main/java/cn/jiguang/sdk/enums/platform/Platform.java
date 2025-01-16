package cn.jiguang.sdk.enums.platform;

import cn.jiguang.sdk.constants.ApiConstants;

public enum Platform {

    android(ApiConstants.Platform.ANDROID, "Android平台"),
    ios(ApiConstants.Platform.IOS, "iOS平台"),
    hmos(ApiConstants.Platform.HMOS, "鸿蒙平台"),
    windows(ApiConstants.Platform.HMOS, "windows平台"),
    web(ApiConstants.Platform.WEB, "web平台"),
    ;

    private String value;
    private String description;

    Platform(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue(){
        return value;
    }

}
