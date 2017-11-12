# appstatus-spring-boot-starter

With `appstatus-spring-boot-starter`, it's easy to enable AppStatus features in your spring boot application !

## appstatus-spring-boot-starter-web
Just add `@EnableAppStatus` annotation to your main config class.
`/status` servlet is created and all ICheck and IPropertyProvider bean instances add to appstatus context.

```java
@EnableAppStatus
@SpringBootApplication
public class Application extends SpringBootServletInitializer { ... }
```

To register a ICheck or IPropertyProvider bean instance you could use :
```java
@AppStatusCheck
public class SampleCheck extends AbstractCheck { ... }

@AppStatusPropertyProvider
public class SamplePropertyProvider implements IPropertyProvider { ... }
```

## appstatus-spring-boot-starter-service
Add `@EnableAppStatusService` annotation to your configuration class to enable appstatus-service-inprocess.
You need to provide ``pointcuts`` parameter to create apspectj advisor on your services. 

```java
@EnableAppStatus
@EnableAppStatusService({
        "execution(public * net.sf.appstatus.boot.demo.service..*.*(..))",
        "execution(public * net.sf.appstatus.boot.demo.controller..*.*(..))" })
@SpringBootApplication
public class Application extends SpringBootServletInitializer { ... }
```

Parameters :

| Name | type | default |
| ---- | ---- | ------- |
| pointcuts      | String[] | {}
| useThreadLocal | boolean  | true
| minMaxDelay    | int      | 10
| log            | boolean  | true
| logFormat      | String   | "${correlationId}\|${group}\|${name}\|${responseTime}\|${cache}\|${status}\|${statusMessage}"


## appstatus-spring-boot-starter-jcache
Let appstatus be your jcache manager and provide hit information to appstatus-service-inprocess with `@EnableAppStatusCache`. 

```java
@EnableAppStatus
@EnableAppStatusService(...)
@EnableAppStatusCache(configFile = "ehcache.xml")
@SpringBootApplication
public class Application extends SpringBootServletInitializer { ... }
```

Parameters :

| Name | type | default |
| ---- | ---- | ------- |
| configFile | String | ehcache.xml

## appstatus-spring-boot-starter-batch
Use  `@EnableAppStatusBatch` to start with appstatus-batch-jdbc.

```java
@EnableAppStatus
@EnableAppStatusBatch(tableName = "appstatus_batch", logInterval = 2000, zombieInterval = 15000)
@SpringBootApplication
public class Application extends SpringBootServletInitializer { ... }
```

Parameters :

| Name | type | default |
| ---- | ---- | ------- |
| logInterval    | int                       | 1000 (ms)
| zombieInterval | int                       | 1000 * 60 * 10 (ms)
| batchDaoClass  | Class<? extends BatchDao> | BatchDao.class
| tableName      | String                    | BATCH

## appstatus-spring-boot-starter-logback
Enable appstatus-logback with `@EnableAppStatusLogback` annotation.

```java
@EnableAppStatus
@EnableAppStatusLogback
@SpringBootApplication
public class Application extends SpringBootServletInitializer { ... }
```
