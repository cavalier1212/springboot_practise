# bean 加載

## 使用@Import({MyImportSelector.class})

* 可以在實作ImportSelector的類裡面添加加載條件


##  用@Conditional即其實是註解加載

*  @Conditional及其下如 @ConditionalOnClass...等等可以判斷條件加載
*  可以多條件疊加，全部為真才加載

* @ConditionalOnBean 和 @ConditionalOnMissingBean：這兩個注解用於檢查容器中是否存在（或不存在）特定的 bean。
* @ConditionalOnClass 和 @ConditionalOnMissingClass：用於根據類的存在與否來決定是否加載某個配置。這對於基於功能可用性的自動配置尤其有用。
* @ConditionalOnProperty：允許根據 Spring 環境中的屬性來條件化地配置 bean。
* @ConditionalOnResource：根據一個資源（如類路徑上的文件）的存在與否來決定是否加載配置。
* @Profile("dev"):可以根據環境加載

```java
// 有資料庫才加載
@Bean
@ConditionalOnClass(name = "com.mysql.jdbc.Driver")
public DruidDataSource dataSource(){
    return new DruidDataSource();
}
```