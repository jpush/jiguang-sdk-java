package cn.jiguang.sdk.enums.callback;

public enum CallbackType {

    RECEIVED(0b1, "送达回执，值为1"),
    CLICKED(0b10, "点击回执，值为2"),
    RECEIVED_AND_CLICKED(0b11, "送达和点击回执，值为3"),
    PUSH(0b1000, "推送成功回执，值为8"),
    PUSH_AND_RECEIVED(0b1001, "推送成功和送达回执，值为9"),
    PUSH_AND_CLICKED(0b1010, "推送成功和点击回执，值为10"),
    PUSH_AND_RECEIVED_AND_CLICKED(0b1011, "推送成功和送达和点击回执，值为11"),
    ;

    private int value;
    private String description;

    CallbackType(int value, String description) {
        this.value = value;
        this.description = description;
    }

}
