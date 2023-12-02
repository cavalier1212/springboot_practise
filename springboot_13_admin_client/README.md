# spring-boot-admin-starter-client


## 設定


```xml
<!-- spring-boot-admin-starter-client -->
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.5.4</version>
</dependency>
```

```yaml
spring:
  boot:
    admin:
      client:
      # 被誰監控
        url: http://localhost
management:
  # 上級控制 這邊不開放 endpoints 拿不到
  endpoint:
    # 基本health 必須
    health:
      show-details: always
  endpoints:
    web: # web 開放控制
      exposure:
      # 開放監控
        include: '*'  # 預設health, * 全部
    enabled-by-default: true # 預設true, 開放給JMX jconsole (終端輸入 jconsole)
```

JMX : 預設幾乎全開放

WEB : health, info

### info可以用java設定
```java
@Component
public class InfoConfig implements InfoContributor {

    @Override
    public void contribute(Builder builder) {
        builder.withDetail("buildTime", 2023);
        Map<String, Object> details = new HashMap<>();
        details.put("runTime", System.currentTimeMillis());
        details.put("date", new Date().toString());
        builder.withDetails(details);
    }

}
```

### health可以用java設定

```java
@Component
public class HealthConfig extends AbstractHealthIndicator{

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        boolean condition = true;
        if(condition){
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("buildTime","2006");
            builder.withDetail("runTime",System.currentTimeMillis());
            builder.withDetail("company","boring");
            builder.withDetails (infoMap);
            builder.status(Status.UP);
         }else{
            builder. status (Status.DOWN);
         }
    }

}

```

### 自訂metrics指標

```java
private Counter counter;
public BookServiceImpl (MeterRegistry meterRegistry){
    meterRegistry.gauge("gauge_name", 12123);
    counter = meterRegistry.counter("XXX使用次數:");
}

// other method call 
counter.increment();

```

### 自訂義端點(Endpoint)

實現如health, info 可以以`/actuator/pay` 取得資訊

```java
@Component
@Endpoint(id="pay")
public class PayEndPoint {
    @ReadOperation
    public Object getPay (){
    //调用业务操作，获取这付相关信息结果，最终return出去
    Map<String, Integer> payMap = new HashMap<>();
    payMap.put("level 1", 103); 
    payMap.put("level 2",315); 
    payMap.put("level 3" ,666); return payMap;
    }
}
```