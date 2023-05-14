

## data source

Hikari 為預設 connection pool
其他內建
TomcatDataSource
Apache Commons DBCP

其他connection pool
可以透過`spring.datasource.type=com.alibaba.druid.pool.DruidDataSource` 設定


## JDBC template

spring 提供的持久化解決方案

## Spring Boot 內置料庫
- H2
- HSQL
- Derby


## H2

內存級資料庫

有訪問介面 : `http://localhost/h2`

初始化須設定一次資料庫
帳號: sa  , 密碼 : 123456 , 
```
spring:
  h2:s
    console:
      path: /h2
      enabled: true
  # 初始化需設定一次，後可以刪除
  datasource:
    url: jdbc:h2:~/test
    hikari:
      username: sa
      password: 123456
      driver-class-name: org.h2.Driver
```

`create table book (id int , type varchar, name varchar, description varchar)`

`insert into book values(1, 'springboot1',  'springboot1',  'springboot1')`

`select * from book`