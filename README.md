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
<!--以5.1.3版本为例-->
<dependencies>
        <!-- jiguang-sdk -->
        <dependency>
            <groupId>io.github.jpush</groupId>
            <artifactId>jiguang-sdk</artifactId>
            <version>5.1.3</version>
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
> 可根据自身情况设置host、proxy和loggerLevel
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
                    .setLoggerLevel(Logger.Level.FULL)
                    .build();
```
使用api示例
* [PushApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/PushApiTest.java)
* [DeviceApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/DeviceApiTest.java)
* [ReportApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/ReportApiTest.java)
* [AdminApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/AdminApiTest.java)
* [GroupPushApi](https://github.com/jpush/jiguang-sdk-java/blob/main/example-for-spring/src/test/java/cn/jiguang/app/api/GroupPushApiTest.java)
