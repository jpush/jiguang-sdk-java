package cn.jiguang.sdk.client;

import cn.jiguang.sdk.bean.schedule.SchedulePushDetailGetResult;
import cn.jiguang.sdk.bean.schedule.SchedulePushPageResult;
import cn.jiguang.sdk.bean.schedule.SchedulePushParam;
import cn.jiguang.sdk.bean.schedule.SchedulePushResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface ScheduleClient {
    /**
     * 创建定时推送
     * 定时最多有效数（当前未过期数）与周期最多有效数（当前未过期数）总数 1000 个，超过后失败。
     * 最多数量私有云可以在部署时配置，默认 1000。
     * 定时任务（single）的最晚时间与定期任务（periodical）的最大跨度时间不限制，但建议不超过 1 年。
     */
    @RequestLine("POST /v3/schedules")
    @Headers("Content-Type: application/json; charset=utf-8")
    SchedulePushResult schedulePush(SchedulePushParam param);

    /**
     * 更新定时任务
     */
    @RequestLine("PUT /v3/schedules/{schedule_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void updateSchedulePush(@Param("schedule_id") String scheduleId, SchedulePushParam param);

    /**
     * 更新定时任务
     */
    @RequestLine("DELETE /v3/schedules/{schedule_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    void deleteSchedulePush(@Param("schedule_id") String scheduleId);

    /**
     * 获取有效的定时任务列表
     * 返回当前请求页的详细的 schedule-task 列表，如未指定 page 则 page 为 1。
     * 排序规则：创建时间，由 schedule-service 完成。
     * 如果请求页数大于总页数，则 page 为请求值，schedules 为空。
     * 每页最多返回 50 个 task，如请求页实际的 task 的个数小于 50，则返回实际数量的 task。
     */
    @RequestLine("GET /v3/schedules?page={page}")
    @Headers("Content-Type: application/json; charset=utf-8")
    SchedulePushPageResult listSchedulePush(@Param("page") Integer page);

    /**
     * 获取定时任务详情
     */
    @RequestLine("GET /v3/schedules/{schedule_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    List<SchedulePushParam> getSchedulePush(@Param("schedule_id") String scheduleId);

    /**
     * 获取某个定时任务的所有消息 id
     */
    @RequestLine("GET /v3/schedules/{schedule_id}/msg_ids")
    @Headers("Content-Type: application/json; charset=utf-8")
    SchedulePushDetailGetResult getSchedulePushDetail(@Param("schedule_id") String scheduleId);

    // ********************* 如果遇到此api没有及时补充字段的情况，可以自行构建json，调用下面的接口 *********************

    @RequestLine("POST /v3/schedules")
    @Headers("Content-Type: application/json; charset=utf-8")
    SchedulePushResult scheduleSend(Object param);
}
