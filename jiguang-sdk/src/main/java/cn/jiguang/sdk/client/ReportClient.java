package cn.jiguang.sdk.client;

import cn.jiguang.sdk.bean.report.*;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReportClient {
    /**
     * 如果一次 API 调用推送有很多对象（比如广播推送），则此 API 返回的统计数据会因为持续有客户端送达而持续增加。
     * 每条推送消息的送达统计数据最多保留一个月。即发起推送请求后从最后一个推送送达记录时间点开始保留一个月，如果保留期间有新的送达，将在这个新送达的时间点起再往后保留一个月。
     */
    @RequestLine("GET /v3/received/detail?msg_ids={msg_ids}")
    @Headers("Content-Type: application/json; charset=utf-8")
    List<ReceivedDetailGetResult> getReceivedDetail(@Param("msg_ids") String msgIds);

    /**
     * 用于查询已推送的一条消息在一组设备上的送达状态。
     */
    @RequestLine("POST v3/status/message")
    @Headers("Content-Type: application/json; charset=utf-8")
    Map<String, MessageStatusGetResult> getMessageStatus(MessageStatusGetParam param);

    /**
     * 此接口获取到详细的厂商通道的数据按照最新数据统计指标返回。
     */
    @RequestLine("GET /v3/messages/detail_new?msg_ids={msg_ids}")
    @Headers("Content-Type: application/json; charset=utf-8")
    List<MessageDetailGetResult> getMessageDetail(@Param("msg_ids") String msgIds);

    /**
     * 提供近 2 个月内某时间段的用户相关统计数据：新增用户、在线用户、活跃用户。
     */
    @RequestLine("GET /v3/users?time_unit=DAY&start={start}&duration={duration}")
    @Headers("Content-Type: application/json; charset=utf-8")
    UserDetailGetResult getUserDetail(@Param("start") LocalDate startDate, @Param("duration") int duration);
}
