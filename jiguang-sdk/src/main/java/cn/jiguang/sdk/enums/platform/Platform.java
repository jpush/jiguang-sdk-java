package cn.jiguang.sdk.enums.platform;

import cn.jiguang.sdk.constants.ApiConstants;

public enum Platform {

    android(ApiConstants.Platform.ANDROID, "Android平台"),
    ios(ApiConstants.Platform.IOS, "iOS平台"),
    hmos(ApiConstants.Platform.HMOS, "鸿蒙平台"),
    quickapp(ApiConstants.Platform.QUICK_APP, "快应用平台");

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
