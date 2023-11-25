# Spring Cache 

## 預設simple引用

> 程式內方案

### 引入依賴

```xml
<!-- cache -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```


### 啟用緩存 (@EnableCaching)

```java
@SpringBootApplication
@EnableCaching
public class SsmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmpApplication.class, args);
    }
}
```
* `@EnableCaching`：這個注解啟用 Spring 的注解驅動的緩存管理功能。這意味著 Spring 會尋找帶有緩存相關注解（如 @Cacheable、@CachePut 等）的方法，並自動應用緩存行為。

### 緩存操作 (@Cacheable)

```java
@Cacheable(value = "book", key = "#id")
public Book getById(Integer id) {
    return bookDao.selectById(id);
}
```
* `@Cacheable`：這個注解表示該方法的結果是可以被緩存的。每當這個方法被調用時，Spring 首先檢查是否已經有緩存了這個方法的結果。
* value = "book"：這個屬性定義了緩存的名稱（或稱為緩存區）。在這個案例中，所有使用這個注解的方法返回的結果都將被存儲在名為 "book" 的緩存中。
* key = "#id"：這個屬性定義了緩存的鍵。在這個案例中，方法參數 id 的值將用作緩存鍵。這意味著對於特定的 id，方法 getById 的結果會被緩存，並且如果有相同 id 的後續調用，將直接從緩存中檢索結果而不是再次執行方法。

### 緩存取得、更新與注意事項

```java
@Service
public class SMSCodeServiceImpl implements SMSCodeService{

    @Autowired
    private CodeUtils codeUtils;

    @Autowired
    private SMSCodeServiceImpl self;

    @Override
    @CachePut(value = "smsCode", key = "#phone" ) // 只會放入緩存
    public String sendCode(String phone);
        return codeUtils.generator(phone);
    }

    @Override
    public boolean checkCode(String phone, String code) {
        //self 自我注入 實現 spring代理，直接呼叫不會被spring所有接管
        String cacheCode = self.getCode(phone);
        System.err.println(cacheCode);
        return code.equals(cacheCode);
    }
    
    // @Cacheable 需要被spring代理調用，class內直接呼叫不會有反應
    @Cacheable(value = "smsCode", key = "#phone" )
    public String getCode(String phone) {
        return null;
    }
}
```

## 使用 ehcache

### dependency

> 程式內方案

```xml
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
</dependency>
```

### application.yml 設定

```yml
spring:
  cache:
    type: ehcache
    ehcache:
      config: classpath:ehcache.xml
      # ehcache.config 預設名稱=>ehcache.xml，可更改
```

### ehcache.xml 設定

* 可作詳細如時間設定、超時、存入硬碟...等等設定
* 每個緩存建議分開設定，以設定中`name`區分，有設定value，則必須設定，沒設定value，則使用默認

## 使用 Redis

需本地有Redis

> 遠端內方案

### dependency

```xml
<!-- Redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 

```yml
spring:
    redis:
        host: localhost
        port: 6379 
    cache:
        type: redis 
        redis:
        use-key-prefix: true # 是否使用前缀名（系统設定前缀名）
        key-prefix: sms_ # 追加白定义前缀名
        time-to-live: 10s # 有效时长
        cache-null-values: false # 是否允许存储空值
```

## memcached

> 遠端內方案

* memcached缺乏認證以及安全管制，這代表應該將memcached伺服器放置在防火牆後
* spring boot 未維護版本，需maven加入包含版本的dependency
* 需java code 設定，java code 使用
* 本地需安裝memcached程式
* memcached客户端选择
  * Memcached Client for Java : 最早期客户端，稳定可靠，用户群广
  * SpyMemcached : 效率更高
  * `Xmemcached` : 并发处理更好

## jetcache

* jetCache是由阿里巴巴开源的通用缓存访问框架
* jetCache对SpringCache进行了封装，在原有功能基础上实现了多级缓存、缓存统计、自动刷新、异步调用、数据报表等功能
* jetCache设定了本地缓存与远程缓存的多级缓存解决方案
* 本地缓存（local）
  * LinkedHashMap
  * Caffeine
* 远程缓存（remote）
  * Redis
  * Tair

## j2cache

* 緩存整合框架
* 配置1級2級緩存，也可以單1級

```xml
<!-- j2cache-spring-boot2-starter 包含 Redis -->
<dependency>
    <groupId>net.oschina.j2cache</groupId>
    <artifactId>j2cache-spring-boot2-starter</artifactId>
    <version>2.8.0-release</version>
</dependency>
<dependency>
    <groupId>net.oschina.j2cache</groupId>
    <artifactId>j2cache-core</artifactId>
    <version>2.8.4-release</version>
</dependency>
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
</dependency>
```

```yml
# 配置使用j2cache （application.yml）
j2cache :
    config-location: j2cache.properties
```

### j2cache.properties

> 配置一级缓存与二级缓存以及一级缓存数据到二级缓存的发送方式

```properties
# 配置1级緩存
j2cache.L1.provider_class = ehcache
ehcache.configXml = ehcache. xml

# 配置1级缓存数据到2级缓存的广播方式：可以使用redis提供的消息订阅模式，也可以使用jgroups多播实现
j2cache.broadcast = net.oschina.j2cache.cache.support.redis.SpringRedisPubSubPolicy

# 配置2级缓存
j2cache.L2.provider_class = net.oschina.j2cache.cache.support.redis.SpringRedisProvider
j2cache.L2.config_section = redis
redis.hosts = localhost:6379
```

### 緩存操作

```java
// 设置使用缓存
@Service
public class SMSCodeServiceImp1 implements SMSCodeService {
    @Autowired
    private CodeUtils codeUtils;
    @Autowired
    private CacheChannel cacheChannel;
    @Override
    public String sendCodeToSMS(String tele) {
        String code = codeUtils.generator(tele);
        cacheChannel.set("sms", tele, code);
        return code;
    }
    @Override
    public boolean checkCode(SMSCode smsCode) {
    String code = cacheChannel.get ("sms", smsCode.getTele()).asString();
    return smsCode. getCode().equals(code);
    }
}
```