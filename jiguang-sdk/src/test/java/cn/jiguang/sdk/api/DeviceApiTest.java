package cn.jiguang.sdk.api;

import cn.jiguang.sdk.bean.device.TestDeviceAddParam;
import cn.jiguang.sdk.bean.device.TestDeviceListParam;
import cn.jiguang.sdk.bean.device.TestDeviceListResult;
import cn.jiguang.sdk.bean.device.TestDeviceUpdateParam;
import feign.Client;
import feign.Request;
import feign.Response;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DeviceApiTest {

    @Test
    public void addTestDeviceSendsExpectedRequest() {
        CapturingClient client = new CapturingClient(200, null);
        DeviceApi api = buildApi(client);

        TestDeviceAddParam param = new TestDeviceAddParam();
        param.setDeviceName("test-device");
        param.setRegistrationId("rid-001");

        api.addTestDevice(param);

        Request request = client.getRequest();
        assertEquals("POST", request.httpMethod().name());
        assertEquals("https://example.com/v3/test/model/add", request.url());
        assertBodyContains(request, "\"device_name\":\"test-device\"");
        assertBodyContains(request, "\"registration_id\":\"rid-001\"");
    }

    @Test
    public void deleteTestDeviceSendsExpectedRequest() {
        CapturingClient client = new CapturingClient(200, null);
        DeviceApi api = buildApi(client);

        api.deleteTestDevice("rid-001");

        Request request = client.getRequest();
        assertEquals("DELETE", request.httpMethod().name());
        assertEquals("https://example.com/v3/test/model/delete/rid-001", request.url());
    }

    @Test
    public void getTestDevicesSendsQueryAndDecodesResult() {
        String responseBody = "{"
                + "\"total\":1,"
                + "\"detail\":[{"
                + "\"device_name\":\"test-device\","
                + "\"device_model\":\"iPhone\","
                + "\"registration_id\":\"rid-001\","
                + "\"registration_time\":\"2025-01-01 00:00:00\","
                + "\"create_time\":\"2025-01-02 00:00:00\""
                + "}],"
                + "\"page\":1,"
                + "\"page_size\":10"
                + "}";
        CapturingClient client = new CapturingClient(200, responseBody);
        DeviceApi api = buildApi(client);

        TestDeviceListParam param = new TestDeviceListParam();
        param.setRegistrationId("rid-001");
        param.setDeviceName("test-device");
        param.setPage(1);
        param.setPageSize(10);

        TestDeviceListResult result = api.getTestDevices(param);

        Request request = client.getRequest();
        assertEquals("GET", request.httpMethod().name());
        assertTrue(request.url().startsWith("https://example.com/v3/test/model/list?"));
        assertTrue(request.url().contains("registration_id=rid-001"));
        assertTrue(request.url().contains("device_name=test-device"));
        assertTrue(request.url().contains("page=1"));
        assertTrue(request.url().contains("page_size=10"));

        assertEquals(Integer.valueOf(1), result.getTotal());
        assertEquals(Integer.valueOf(1), result.getPage());
        assertEquals(Integer.valueOf(10), result.getPageSize());
        assertNotNull(result.getDetail());
        assertEquals(1, result.getDetail().size());
        assertEquals("test-device", result.getDetail().get(0).getDeviceName());
        assertEquals("iPhone", result.getDetail().get(0).getDeviceModel());
        assertEquals("rid-001", result.getDetail().get(0).getRegistrationId());
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0), result.getDetail().get(0).getRegistrationTime());
        assertEquals(LocalDateTime.of(2025, 1, 2, 0, 0), result.getDetail().get(0).getCreateTime());
    }

    @Test
    public void updateTestDeviceSendsExpectedRequest() {
        CapturingClient client = new CapturingClient(200, null);
        DeviceApi api = buildApi(client);

        TestDeviceUpdateParam param = new TestDeviceUpdateParam();
        param.setRegistrationId("rid-001");
        param.setDeviceName("renamed-device");

        api.updateTestDevice(param);

        Request request = client.getRequest();
        assertEquals("PUT", request.httpMethod().name());
        assertEquals("https://example.com/v3/test/model/update", request.url());
        assertBodyContains(request, "\"registration_id\":\"rid-001\"");
        assertBodyContains(request, "\"device_name\":\"renamed-device\"");
    }

    private static DeviceApi buildApi(Client client) {
        return new DeviceApi.Builder()
                .setClient(client)
                .setHost("https://example.com")
                .setAppKey("appKey")
                .setMasterSecret("masterSecret")
                .build();
    }

    private static void assertBodyContains(Request request, String expected) {
        assertNotNull(request.body());
        String body = new String(request.body(), StandardCharsets.UTF_8);
        assertTrue(body, body.contains(expected));
    }

    private static class CapturingClient implements Client {

        private final int status;
        private final String body;
        private Request request;

        private CapturingClient(int status, String body) {
            this.status = status;
            this.body = body;
        }

        @Override
        public Response execute(Request request, Request.Options options) throws IOException {
            this.request = request;
            return buildResponse(request);
        }

        private Request getRequest() {
            return request;
        }

        private Response buildResponse(Request request) {
            Map<String, Collection<String>> headers = body == null
                    ? Collections.<String, Collection<String>>emptyMap()
                    : Collections.<String, Collection<String>>singletonMap(
                    "Content-Type",
                    Collections.<String>singletonList("application/json; charset=utf-8")
            );
            Response.Builder builder = Response.builder()
                    .status(status)
                    .reason("OK")
                    .headers(headers)
                    .request(request);
            if (body != null) {
                builder.body(body, StandardCharsets.UTF_8);
            }
            return builder.build();
        }

    }

}
