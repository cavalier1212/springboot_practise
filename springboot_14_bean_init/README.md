# Bean 的加載

1. xml+<bean/>
2. xml:context+注解（@Component+4个@Bean）
3. 配置類＋掃描+注解（@Component +4个@Bean）
   * @Bean定义FactoryBean接口
   * @ImportResource
   * @Configuration注解的proxyBeanMethods属性
4. @Import導入bean的類
   * @Import導入配置類
5. AnnotationConfigApplicationContext調用register方法
6. @Import導入ImportSelector接口
7. @Import導入ImportBeanDefinitionRegistrar接口
8. @Import導入BeanDefinitionRegistryPostProcessor接口


## 使用xml配置並手動加載

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
// 用id查找，沒id會以全路徑加#編號
Object bookService = ctx.getBean("bookService");
```

## 使用Spring管理加載

* 設定掃描
```xml
<context:component-scan base-package="com.example.bean"/>
```

* 加載檔案設定掃描檔案
```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
for (String name : ctx.getBeanDefinitionNames()) {
    System.out.println("bean name : "+name);
}
```

* 在的base-package底下使用`@Component`及其衍生注解`@Controller`、`@Service`、`@Repository`、`@Configuration`定義bean...
Spring就會去掃描、加載

## 使用Annotation 掃描加載

* 配置

```java
// @Configuration 要被掃描才加
// @Configuration
// 可傳入陣列{"A,B,C"}
@ComponentScan({"com.example.bean"})
public class SpringConfig3 {

}

// 改成AnnotationConfigApplicationContext加載
public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig3.class);
    for (String name : ctx.getBeanDefinitionNames()) {
        System.out.println("bean name : "+name);
    }
}
```

## FactoryBean 創建Bean 

* 創建時可加強Bean

* 創建工廠Bean
```java
public class DogFactoryBean implements FactoryBean<Dog> {

    @Override
    @Nullable
    public Dog getObject() throws Exception {
       Dog dog = new Dog();
       // 此處可加強
       return dog;
    }

    @Override
    @Nullable
    public Class<?> getObjectType() {
        return Dog.class;
    }
}
```
* 取得Bean

```java
// DogFactoryBean 返回 Dog

@Bean
public DogFactoryBean dog() {
    return new DogFactoryBean(); // 此處可加強
}
```

## 

* 導入對應XML配置好的Bean
```java
@Configuration
@ImportResource("applicationContext1.xml") // 此處導入
public class SpringConfig32 {
    // DogFactoryBean 返回 Dog
    @Bean
    public DogFactoryBean dog() {
        return new DogFactoryBean(); // 此處可加強
    }
}
```

## Configuration的proxyBeanMethods屬性

proxyBeanMethods = true 設定對象會被代理(proxy)

需在類中調用 或 用類Bean使用

```java
// proxyBeanMethods = true(預設) 拿到的bean對象會是初始創建的同一個(singleton)
// proxyBeanMethods = false 拿到的bean對象 每次都是新的
@Configuration(proxyBeanMethods = true)
public class SpringConfig33 {

    @Bean
    public Cat cat() {
        return new Cat();
    }
}
//////
public class App33 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig33.class);
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println("bean name : "+name);
        }
        System.out.println("----------------------");
        SpringConfig33 springConfig33 = ctx.getBean("springConfig33", SpringConfig33.class);
        
        System.out.println(springConfig33.cat());
        System.out.println(springConfig33.cat());
        System.out.println(springConfig33.cat());
    }
}
```

## import 乾淨的class

* 使用@Import注解導入要注入的bean class
* 被導入的bean無需添加任何聲明
* 此形式可以有效的降低源代码与Spring技术的耦合度，在spring技术底层及诸多框架的整合中大量使用
* 適合整合其他技術或外部引入

* Import 加載為全路徑加載 com.example.bean.`Dog`, 
com.example.bean.config.`DBConfig`
* 可以導入配置類(@Configuration)，且無需加@Configuration，就可以載入配置類與其內的bean

```java
@Import({Dog.class,DBConfig.class})
public class SpringConfig4 {
}
```

## 使用上下文對象(context)在容器初始化完畢後注入bean

* 需使用 AnnotationConfigApplicationContext

```java
public class App5 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig5.class);

        ctx.registerBean("tom",Cat.class, 0);
        ctx.registerBean("tom",Cat.class, 1);
        ctx.registerBean("tom",Cat.class, 2);// 最後一個為主 後蓋前
        
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println("bean name : "+name);
        }
        System.out.println("----------------------");
        System.out.println(ctx.getBean(Dog.class));
        System.out.println(ctx.getBean("tom"));
    }
}
```
## @Import(MyImportSelector.class)

* 導入實作了ImportSelector介面的類，實作對導入來源(取得MetaData)的編程式處理
* 可以取得導入類資料後判斷加載(誰導入就可拿誰的資料判斷)

導入類
```java
@Import(MyImportSelector.class)
public class SpringConfig6 {
}
```
ImportSelector實現類
```java
public class MyImportSelector implements ImportSelector{

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        boolean flag = importingClassMetadata.hasAnnotation("org.springframework.context.annotation.Import");
        if(flag){
            return new String[]{"com.example.bean.Dog"};
        }
        return new String[]{"com.example.bean.Cat"};
    }
}
```

## @Import(MyImportBeanDefinitionRegistrar.class)

* 導入實現了 ImportBeanDefinitionRegistrar接口的類，通過 BeanDefinition的註冊器註冊實名bean，
實現對容器中bean的裁定，例如對現有bean的覆蓋，進而達成不修改源代碼的情況下更換實現的效果
* 管控到bean創建定義的層面

```java
@Import(MyImportBeanDefinitionRegistrar.class)
public class SpringConfig7 {
}
```
```java
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition (Dog.class).getBeanDefinition();//取得方式可改
        registry.registerBeanDefinition("bigYellow", beanDefinition);// bean名稱    
	}
}
```

## @Import(MyBeanDefinitionRegistryPostProcessor.class)

* bean定義的後處理器
* 導入實現了BeanDefinitionRegistry`Post`Processor接口的類，通過BeanDefinition的註冊器註冊實名bean， 實現對容器中bean的最終裁定

* 適用於你需要決定最後加載誰


import類
```java
// MyPostProcessor最後動作
// ImportBeanDefinitionRegistrar加載順序大於BookServiceImpl1.class
// 如果有兩個 ImportBeanDefinitionRegistrar 則後蓋前
@Import({MyImportBeanDefinitionRegistrar2.class,MyImportBeanDefinitionRegistrar.class, BookServiceImpl1.class, MyPostProcessor.class})
public class SpringConfig8 {
}
```
設定postPostProcessor
```java
public class MyPostProcessor implements BeanDefinitionRegistryPostProcessor{

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition (BookServiceImpl4.class).getBeanDefinition();
        registry.registerBeanDefinition("bookService", beanDefinition);
    }

}
```