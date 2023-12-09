package com.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.druid.pool.DruidDataSource;

// @Import({MyImportSelector.class})
// @Import({Mouse.class})
@ComponentScan("com.example.bean")
public class SpringConfig {

    // @Bean
    // // @ConditionalOnClass(Mouse.class) // 可傳class 或 String全路徑
    // // @ConditionalOnClass(name = "com.example.bean.Mouse")
    // // @ConditionalOnMissingClass("com.example.bean.Mouse")
    // @ConditionalOnBean(name = "Jerry") // component 名稱
    // // @ConditionalOnMissingClass("com.example.bean.Dog")
    // @ConditionalOnNotWebApplication
    // public Cat tom(){
    //     return new Cat();
    // }

    // 有資料庫才加載
    @Bean
    @ConditionalOnClass(name = "com.mysql.jdbc.Driver")
    public DruidDataSource dataSource(){
        return new DruidDataSource();
    }
}
