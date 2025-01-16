package cn.jiguang.sdk.client;

import cn.jiguang.sdk.bean.push.PushParam;
import cn.jiguang.sdk.bean.push.PushResult;
import cn.jiguang.sdk.bean.push.other.CidGetResult;
import cn.jiguang.sdk.bean.push.other.QuotaGetResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface PushClient {
    /**
     * 创建推送
     */
    @RequestLine("POST /v3/push")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushResult send(PushParam param);

    /**
     * 获取推送 CID
     * cid 是用于防止 api 调用端重试造成服务端的重复推送而定义的一个推送参数。
     * 用户使用一个 cid 推送后，再次使用相同的 cid 进行推送，则会直接返回第一次成功推送的结果，不会再次进行推送。
     * cid 的有效期为 1 天。CID 的格式为：{appkey}-{uuid}
     * 在使用 cid 之前，必须通过接口获取你的 cid 池。
     */
    @RequestLine("GET /v3/push/cid?type=push&count={count}")
    @Headers("Content-Type: application/json; charset=utf-8")
    CidGetResult getCidForPush(@Param("count") int count);

    /**
     * 推送校验
     * 该 API 只用于验证推送调用是否能够成功，与推送 API 的区别在于：不向用户发送任何消息。
     */
    @RequestLine("POST /v3/push/validate")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushResult validateSend(PushParam param);

    /**
     * 推送撤销
     * 撤销操作首先会从服务端尝试撤销（Android 消息，排队中 / 发送中状态可以服务端撤销；iOS 消息，排队中状态可以服务端撤销）；其次，针对 Push SDK（MTPush Android SDK v1.2.0 及以上和 MTPush iOS SDK v1.1.0 及以上），会尝试从设备端撤销已展示但未被点击的消息。
     */
    @RequestLine("DELETE /v3/push/{msg_id}")
    @Headers("Content-Type: text/plain")
    void withdrawMessage(@Param("msg_id") String messageId);

    /**
     * 查询厂商配额
     * 实时查询指定应用相关各厂商可用配额、剩余配额数据。
     */
    @RequestLine("GET /v3/push/quota")
    @Headers("Content-Type: application/json; charset=utf-8")
    QuotaGetResult getQuota();

    // ********************* 如果遇到此api没有及时补充字段的情况，可以自行构建json，调用下面的接口 *********************

    @RequestLine("POST /v3/push")
    @Headers("Content-Type: application/json; charset=utf-8")
    PushResult send(Object param);
}
