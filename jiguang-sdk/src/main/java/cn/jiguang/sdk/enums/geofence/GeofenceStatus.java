package cn.jiguang.sdk.enums.geofence;

public enum GeofenceStatus {

    in("in","进入围栏"),
    out("out","离开围栏");

    private String value;
    private String description;

    GeofenceStatus(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
