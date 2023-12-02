# Spring Boot Admin

開源項目位置 https://github.com/codecentric/spring-boot-admin

## 安裝步驟

1. 加入dependency
```xml
<!-- 必須為web項目 -->
<!-- version num 需與 spring boot 對齊 -->
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-server</artifactId>
    <version>3.1.5</version>
</dependency>
```
2. 配置port num 避免衝突
3. 入口點加入`@EnableAdminServer`

