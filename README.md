# jiguang-sdk-java

这是 Jiguang REST API 的 Java 版本封装开发包，是由极光推送官方提供的，一般支持最新的 API 功能。

## 1. 集成
引入sdk包
```xml
<!--以5.1.13-customize版本为例-->
<dependencies>
        <!-- jiguang-sdk -->
        <dependency>
            <groupId>io.github.jpush</groupId>
            <artifactId>jiguang-sdk</artifactId>
            <version>5.1.13-customize</version>
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
## 4. 第三方库引用说明
```
    <properties>
        <okhttp.version>4.12.0</okhttp.version>
        <lombok.version>1.18.30</lombok.version>
        <feign-core.version>13.1</feign-core.version>
        <feign-form.version>3.8.0</feign-form.version>
        <jackson-datatype-jsr310.version>2.15.3</jackson-datatype-jsr310.version>
    </properties>

    <dependencies>
        <!-- lombok 打印日志用，需要额外引用实现slfj接口的日志框架，如log4j-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        <!-- feign 网络请求用-->
        <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-core</artifactId>
            <version>${feign-core.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.openfeign.form</groupId>
            <artifactId>feign-form</artifactId>
            <version>${feign-form.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-slf4j</artifactId>
            <version>${feign-core.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-okhttp</artifactId>
            <version>${feign-core.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-jackson</artifactId>
            <version>${feign-core.version}</version>
        </dependency>
        <!-- okhttp 配合feign网络请求用-->
        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>${okhttp.version}</version>
        </dependency>
        <!-- jackson-datatype 配合feign网络请求用-->
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jsr310</artifactId>
            <version>${jackson-datatype-jsr310.version}</version>
        </dependency>
        <!-- SM2 国密加密用-->
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcpkix-jdk15on</artifactId>
            <version>1.68</version>
        </dependency>
    </dependencies>
```
