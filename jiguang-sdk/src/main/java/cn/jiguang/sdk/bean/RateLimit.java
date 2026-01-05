package cn.jiguang.sdk.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 限流信息
 */
@Data
public class RateLimit implements Serializable {

    /**
     * 当前 AppKey 一个时间窗口内可调用次数
     * 响应头: x-rate-limit-limit
     */
    private Integer limit;

    /**
     * 当前时间窗口剩余的可用次数
     * 响应头: x-rate-limit-remaining
     */
    private Integer remaining;

    /**
     * 距离时间窗口重置剩余的秒数
     * 响应头: x-rate-limit-reset
     */
    private Integer reset;
}

