# jiguang-sdk-java

这是 Jiguang REST API 的 Java 版本封装开发包，是由极光推送官方提供的，一般支持最新的 API 功能。

对应的 REST API 文档：
* [REST API - Push](https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push)
* [REST API - Device](https://docs.jiguang.cn/jpush/server/push/rest_api_v3_device)
* [REST API - Report](https://docs.jiguang.cn/jpush/server/push/rest_api_v3_report)
* [REST API - Admin](https://docs.jiguang.cn/jpush/server/push/rest_api_admin_api_v1)
* [REST API - GroupPush](https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push_grouppush)

支持 Java JDK 1.8 及其以上版本。
> 支持 Java JDK 1.6 版本：[jpush-api-java-client](https://github.com/jpush/jpush-api-java-client)，但不再更新。

## 1. 集成
引入sdk包
```xml
<!--以5.2.9版本为例-->
<dependencies>
        <!-- jiguang-sdk -->
        <dependency>
            <groupId>io.github.jpush</groupId>
            <artifactId>jiguang-sdk</artifactId>
            <version>5.2.9</version>
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
            .setAppKey(appKey)
            .setMasterSecret(masterSecret)
            .build();

        DeviceApi deviceApi = new DeviceApi.Builder()
            .setAppKey(appKey)
            .setMasterSecret(masterSecret)
            .build();

        ReportApi reportApi = new ReportApi.Builder()
            .setAppKey(appKey)
            .setMasterSecret(masterSecret)
            .build();

        // devKey和devSecret在极光官网-右上角-个人主页获取
        AdminApi adminApi = new AdminApi.Builder()
            .setDevKey(devKey)
            .setDevSecret(devSecret)
            .build();

        // groupKey和groupMasterSecret在极光官网-分组应用控制台获取
        GroupPushApi groupPushApi = new GroupPushApi.Builder()
            .setGroupKey(groupKey)
            .setGroupMasterSecret(groupMasterSecret)
            .build();
        
        
        // 其他可自定义演示
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient().newBuilder()
                // .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy_host", proxy_port))) // 可自定义代理，可选
                .build();

        PushApi pushApi = new PushApi.Builder()
                .setClient(new OkHttpClient(okHttpClient)) // sdk默认使用的feign-okhttp，可自定义，可选
                .setOptions(new Request.Options(10, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, false)) // 可自定义超时参数，可选
                .setRetryer(new Retryer.Default(10, 10, 10)) // 可自定义重试参数，可选
                .setLoggerLevel(Logger.Level.FULL) // 可自定义日志打印级别，可选
                .setAppKey(appKey) // 必填
                .setMasterSecret(masterSecret) // 必填
                .build();
```
使用api示例
* [PushApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/PushApiTest.java)
* [DeviceApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/DeviceApiTest.java)
* [ReportApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/ReportApiTest.java)
* [AdminApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/AdminApiTest.java)
* [GroupPushApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/GroupPushApiTest.java)

## 3. 推送失败
推送失败会抛出异常，可对下面的类异常捕获后进行业务处理
```java
cn.jiguang.sdk.exception.ApiErrorException
```
* 排查问题，请务必`setLoggerLevel(Logger.Level.FULL)`，查看feign日志
  > 打印类为feign.Logger，从---> POST，到<--- END HTTP，参考下图
* 如需技术支持，请提供使用环境、复现步骤、示例代码、日志信息
<img width="1184" height="658" alt="image" src="https://github.com/user-attachments/assets/33d943b8-f0df-4c11-8bde-6c17cf93dcb9" />

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
