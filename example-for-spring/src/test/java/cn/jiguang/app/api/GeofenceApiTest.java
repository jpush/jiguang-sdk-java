package cn.jiguang.app.api;

import cn.jiguang.sdk.api.GeofenceApi;
import cn.jiguang.sdk.bean.geofence.*;
import cn.jiguang.sdk.enums.geofence.GeofenceStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GeofenceApiTest {

    @Autowired
    private GeofenceApi geofenceApi;

    @Test
    public void create() {
        GeofenceCreateParam.Center center = new GeofenceCreateParam.Center("114.045955421213", "22.58120713159671");
        GeofenceCreateParam param = new GeofenceCreateParam();
        param.setName("地理围栏测试0001");
        param.setCenter(center);
        param.setRadius(100);
        param.setStatus(GeofenceStatus.in);
        param.setRepeat(false);
        param.setType(1);
        param.setExpiration(LocalDateTime.now().plusDays(1));
        GeofenceCreateResult result = geofenceApi.create(param);
        log.info("result:{}", result);
    }

    @Test
    public void update() {
        GeofenceCreateParam.Center center = new GeofenceCreateParam.Center("114.045955421213", "22.58120713159671");
        GeofenceCreateParam param = new GeofenceCreateParam();
        param.setName("地理围栏测试00001");
        param.setCenter(center);
        param.setRadius(100);
        param.setStatus(GeofenceStatus.in);
        param.setRepeat(false);
        param.setType(1);
        param.setExpiration(LocalDateTime.now().plusDays(1));
        geofenceApi.update("228f886332", param);
    }

    @Test
    public void get() {
        GeofenceGetParam param = new GeofenceGetParam();
        param.setGeofenceId("c795c465d6");
        GeofenceGetResult result = geofenceApi.get(param);
        log.info("result:{}", result);
    }

    @Test
    public void delete() {
        geofenceApi.delete("228f886332");
    }

    @Test
    public void page() {
        GeofencePageParam param = new GeofencePageParam();
        param.setPage(1);
        param.setSize(20);
        param.setName("地理围栏测试");
        GeofencePageResult result = geofenceApi.page(param);
        log.info("result:{}", result);
    }

    @Test
    public void getOutlines() {
        log.info("result:{}", geofenceApi.getOutlineResult());
    }

}
