package cn.jiguang.sdk.enums.timeunit;

public enum TimeUnit {

    DAY("day", "为day时，point不能存在"),
    WEEK("WEEK", "为week时，point取值范围[MON,TUE,WED,THU,FRI,SAT,SUN]"),
    MONTH("MONTH", "为month时，point取值范围[01,02,03......31]");

    private String value;
    private String description;

    TimeUnit(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
