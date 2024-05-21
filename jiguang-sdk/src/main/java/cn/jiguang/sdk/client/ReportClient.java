package cn.jiguang.sdk.client;

import cn.jiguang.sdk.bean.report.*;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * (<a href="https://docs.jiguang.cn/jpush/server/push/rest_api_v3_report">REST API - Report</a>)
 */
public interface ReportClient {

    @RequestLine("GET /v3/received/detail?msg_ids={msg_ids}")
    @Headers("Content-Type: application/json; charset=utf-8")
    List<ReceivedDetailGetResult> getReceivedDetail(@Param("msg_ids") String msgIds);

    @RequestLine("POST v3/status/message")
    @Headers("Content-Type: application/json; charset=utf-8")
    Map<String, MessageStatusGetResult> getMessageStatus(MessageStatusGetParam param);

    @RequestLine("GET /v3/messages/detail?msg_ids={msg_ids}")
    @Headers("Content-Type: application/json; charset=utf-8")
    List<MessageDetailGetResult> getMessageDetail(@Param("msg_ids") String msgIds);

    @RequestLine("GET /v3/users?time_unit=DAY&start={start}&duration={duration}")
    @Headers("Content-Type: application/json; charset=utf-8")
    UserDetailGetResult getUserDetail(@Param("start") LocalDate startDate, @Param("duration") int duration);

}
