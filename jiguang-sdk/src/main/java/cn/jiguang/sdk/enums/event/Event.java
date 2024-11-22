package cn.jiguang.sdk.enums.event;

public enum Event {

    start("start", "创建"),
    update("update", "更新"),
    end("end", "结束");

    private String value;
    private String description;

    Event(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
