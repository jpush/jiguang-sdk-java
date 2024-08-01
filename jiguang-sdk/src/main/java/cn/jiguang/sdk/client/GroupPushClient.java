package cn.jiguang.sdk.client;

import cn.jiguang.sdk.bean.push.GroupPushSendParam;
import cn.jiguang.sdk.bean.push.GroupPushSendResult;
import feign.Headers;
import feign.RequestLine;

/**
 * (<a href="https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push_grouppush">REST API - GroupPush</a>)
 */
public interface GroupPushClient {

    @RequestLine("POST /v3/grouppush")
    @Headers("Content-Type: application/json; charset=utf-8")
    GroupPushSendResult send(GroupPushSendParam param);

}
