# Bean 的依賴屬性配置

* 目的 : 不強制被加載，解離@Component、properties
* 模擬Spring Boot對技術的載入自動裝配，如Redis
* 在要用的類申明加載，如`@Import`
* properties有對應類，且不強制加載，誰使用自己加上`@EnableConfigurationProperties(Clazz.class)`


* 將業務功能bean運作所需的資源抽取成獨立的屬性類別（****** Properties），設定讀取設定檔訊息
* 模擬Spring Boot導入技術的properties，如`spring.data.redis.port = 6379`，有在設定檔設定則讀設定檔，沒有則使用預設值

```java
// @Component // 改為時呼叫誰幫此類加載成Component
// @ConfigurationProperties(prefix = "spring.data.redis")
@ConfigurationProperties(prefix = "cartoon") // 是Bean 才能讀配置
@Data
public class CartoonProperties {
    private Cat cat;
    private Mouse mouse; 
}
```
* 使用`@EnableConfigurationProperties`註解設定使用屬性類別時載入bean
* 模擬Spring Boot 對技術的自動裝配類(AutoConfiguration)，Conditional對應maven設定載入的bean，如果有則自動裝配

```java
@Data
// @ConditionalOnClass(Clazz.class) // 條件符合才加載以下properties
@EnableConfigurationProperties(CartoonProperties.class) // 讀取並加載成Bean，被加載的不用申明@Bean or @Component
public class CartoonCatAndMouse {
    private Cat cat;
    private Mouse mouse;
    
    private CartoonProperties cartoonProperties;

    public CartoonCatAndMouse(CartoonProperties cartoonProperties) {
        this.cartoonProperties = cartoonProperties;
        this.cat = cartoonProperties.getCat() != null ? cartoonProperties.getCat(): new Cat();
        this.mouse = cartoonProperties.getMouse() != null ? cartoonProperties.getMouse(): new Mouse();
        this.cat.setAge(cartoonProperties.getCat().getAge() != null ? cartoonProperties.getCat().getAge() : 2);
        this.cat.setName(cartoonProperties.getCat().getName() != null ? cartoonProperties.getCat().getName() : "Tom");
        this.mouse.setAge(cartoonProperties.getMouse().getAge() != null ? cartoonProperties.getMouse().getAge() : 1);
        this.mouse.setName(cartoonProperties.getMouse().getName() != null ? cartoonProperties.getMouse().getName() : "Jerry");
    }

    public void run(){
        System.out.println(this.cat.getAge()+"歲的"+this.cat.getName()+"正追著"+this.mouse.getAge()+"歲的"+this.mouse.getName());
    }
}
```
* 載入自動裝配檔(AutoConfiguration)

```java
@SpringBootApplication
@Import(CartoonCatAndMouse.class) // 加載自動裝配類
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);;
		CartoonCatAndMouse cartoon = ctx.getBean(CartoonCatAndMouse.class);
		cartoon.run();
	}
}
```

## Spring Boot 對每個技術實現的裝配

1. 先開發若干種技術的標準實現

2. Spring Boot啓動時加載所有的技術實現對應的自動配置類(AutoConfiguration)

3. 檢測每個自動配置類(AutoConfiguration)的加載條件是否滿足並進行對應的初始化，如`@ConditionalXXXX`

4. 條件檢符合則去加載該技術的properties,Bean

5. 切記是先加載所有的外部資源，然後根據外部資源進行條件比對

## 模擬用spring.factories自動裝配

* spring.factories
> * Spring Boot 3 不支持!!!
```text
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
    com.example.springboot_16_bean_propertise.bean.CartoonCatAndMouse
```

* 移除@Import(CartoonCatAndMouse.class)

```java
@SpringBootApplication
// 排除裝配
// @Import(CartoonCatAndMouse.class)
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);;
		CartoonCatAndMouse cartoon = ctx.getBean(CartoonCatAndMouse.class);
		cartoon.run();
	}
}
```

* 排除裝配

```yaml
spring:
  autoconfigure:
    exclude:
    - com.example.springboot_16_bean_propertise.bean.CartoonCatAndMouse
```

```java
@SpringBootApplication(excludeName = "com.example.springboot_16_bean_propertise.bean.CartoonCatAndMouse")
```