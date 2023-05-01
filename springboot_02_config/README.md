### 設定檔配置階層
* 臨時屬性
  * 開啟jar檔時換端口  
  `java -jar xxxxx.jar --server.port=8080`
  * 可繼續串聯 application 設定 如 :  
  `--spring.datasource.druid.username=xxxx`
* 參數傳遞
  * 可以在IDE設置
  * 可以在application入口 寫 `String[] args` 設定值陣列
  * 也可以斷開取得設定值陣列`SpringApplication.run(SsmpApplication.class);` 移除 `args`
* config文件夾application檔
  * 優先序高於正常位置的application檔
  * 設定為互補，高階優先
>* 正常位置為開發者使用
>* config文件夾為PM使用
* jar 檔同層application設定檔
  * 優先序高於config文件夾與專案內設定
  * 專案**運維階段**使用
* jar 檔同層config文件夾內application設定檔
  * 優先序高於jar 檔同層application設定檔
  * **運維組長**
  * 最高級別

>開發環境檔案結構也適用(專案同層)

總結
SpringBoot中4及配置文件
1. file : config/application.yml(高)
2. file : application.yml
3. classpath : config/application.yml
4. classpath : application.yml(低)

### 指定到特定資料夾的設定檔(自定義配置文件)
* 可以是任意命名的設定檔(xxx.yml、xxx.application)
* `java -jar oxox.jar --spring.config.location=/path/to/config/`
* /path/to/config/ 可以是相對路徑(./xxx.yml)、絕對路徑(c:xxx/xxx/xxx.application)
> 為服務常用，配置文件統一管理

### 多環境開發(dev、pro、test)
* 以`---`區分堆區分多個區塊
* 第一個區塊設定開發環境、共用設定
* 其他則是各個開發環境設置
```
spring:
  profiles:
    active: dev
---
server:
  port: 89
spring:
  config:
    activate:
      on-profile:
      - pro
---
server:
  port: 82
spring:
  config:
    activate:
      on-profile:
      - dev
---
server:
  port: 82
spring:
  config:
    activate:
      on-profile:
      - test
```
### 多配置文件

1. application.yml(主環境，共用屬性)
2. application-pro.yml
3. application-dev.yml
4. application-test.yml

格式固定

在application.yml中設定以下，指定設定檔
```
spring:
  profiles:
    active: dev
```


> yml、properties檔 都通用 ! 僅格式不同

### 多環境分组管理
* 根據功能拆分設定檔
* **主環境application.yml設定與其他設定檔重複，依加載順序最後的為主**

1. application.yml
2. application-dev.yml
3. application-devMVC.yml
4. application-devLog.yml

group設定甚麼環境讀甚麼檔

group 替代了 include屬性

application.yml 配置以下
```
spring:
  profiles:
    active: dev
    group:
      "dev": devMVC,devLog
      "pro": proMVC,proLog
```

### maven 與 SpringBoot 多環境開發
* 以maven為主
* SpringBoot以@..@，讀取Maven properties
* 在IDE可能回有緩存問題，clean也沒有用，要自己手動compile才會有效