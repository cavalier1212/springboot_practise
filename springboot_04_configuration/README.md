`@ConfigurationProperties(prefix = "XXXXX")` 可以綁定application.yml 的設定，也能綁定第三方套件

`@EnableConfigurationProperties` 會把 ServerConfig 實體化 ，會與ServerConfig `@Component` 衝突，不能實體化兩個Bean

`@EnableConfigurationProperties` 與 `@Component` 衝突，一個類不能同時有這兩個


### 鬆散綁定

`@ConfigurationProperties(prefix = "XXXXX")` 為鬆散綁定，不在乎文字間的符號(_-)、大小寫

官方較建議 : **kebab-case** (aaa-bbb)

>`@Value` **不支持** 鬆散綁定

### 計量單位
> JDK8

```
// 時間
@DurationUnit(ChronoUnit.MINUTES)
private Duration serverTimeout;
// 儲存量
@DataSizeUnit(DataUnit.MEGABYTES)
private DataSize dataSize;
```

### application.yml驗證
* 可驗證表單
```
<!-- 數據驗證依賴 Spring Boot 2.3.0 後 spring-boot-starter-web 會包含，這邊是單獨使用 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

spring-boot-starter-validation 包含 javax.validation api 及 hibernate validator
