package com.example.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DBConfig {
    
    // 使用@Bean定義第三方bean，並將所在類定義為配置類或Bean
    @Bean
    public DruidDataSource dbDataSource() {
        DruidDataSource dds = new DruidDataSource();
        return dds;
    }
}
