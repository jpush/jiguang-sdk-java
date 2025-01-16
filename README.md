# jiguang-sdk-java

这是 Jiguang REST API 的 Java 版本封装开发包，是由极光推送官方提供的，一般支持最新的 API 功能。

## 1. 集成
引入sdk包
```xml
<!--以5.1.12-customize版本为例-->
<dependencies>
        <!-- jiguang-sdk -->
        <dependency>
            <groupId>io.github.jpush</groupId>
            <artifactId>jiguang-sdk</artifactId>
            <version>5.1.12-customize</version>
        </dependency>
</dependencies>
```
引入log包
> 注意项目中已引用了logback、log4j、commons-logging等实现slfj接口的日志框架，则不需要配置。例如'example-for-spring'中引入了spring，自带logback框架，就不需要再配置。
```xml
<!--以logback为例-->
<dependencies>
    <!-- logback -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.11</version>
    </dependency>
</dependencies>
```
## 2. Api
创建api对象
> 可根据自身情况设置client、host和loggerLevel
```java
        // appKey和masterSecret在极光官网-应用控制台获取
        PushApi pushApi = new PushApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();

        DeviceApi deviceApi = new DeviceApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();

        ReportApi reportApi = new ReportApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();

        ScheduleApi scheduleApi = new ScheduleApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();

        GeofenceApi geofenceApi = new GeofenceApi.Builder()
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();        
        
        // 设置client
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient().newBuilder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy_host", proxy_port))) // set proxy
                .connectTimeout(5, TimeUnit.SECONDS) // set connect timeout
                .build();
        OkHttpClient client =new OkHttpClient(okHttpClient);            
            
        PushApi pushApi = new PushApi.Builder()
                .setClient(new OkHttpClient(client))
                .setHost(host)
                .setAppKey(appKey)
                .setMasterSecret(masterSecret)
                .setLoggerLevel(Logger.Level.FULL)
                .build();
```
使用api示例
* [PushApi](https://github.com/jpush/jiguang-sdk-java/blob/customize/example-for-spring/src/test/java/cn/jiguang/app/api/PushApiTest.java)
* [DeviceApi](https://github.com/jpush/jiguang-sdk-java/blob/customize/example-for-spring/src/test/java/cn/jiguang/app/api/DeviceApiTest.java)
* [ReportApi](https://github.com/jpush/jiguang-sdk-java/blob/customize/example-for-spring/src/test/java/cn/jiguang/app/api/ReportApiTest.java)
* [ScheduleApi](https://github.com/jpush/jiguang-sdk-java/blob/customize/example-for-spring/src/test/java/cn/jiguang/app/api/ScheduleApiTest.java)
* [GeofenceApi](https://github.com/jpush/jiguang-sdk-java/customize/customize/example-for-spring/src/test/java/cn/jiguang/app/api/GeofenceApiTest.java)
## 3. 推送失败
推送失败会抛出异常，可对下面的类异常捕获后进行业务处理
```java
cn.jiguang.sdk.exception.ApiErrorException
```