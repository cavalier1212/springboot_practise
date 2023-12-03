package com.example.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.bean.DogFactoryBean;

// @Configuration 要被掃描才加
// @Configuration
// 可傳入陣列{"A,B,C"}
@ComponentScan({"com.example.bean"})
@Configuration
public class SpringConfig3 {

    // DogFactoryBean 返回 Dog
    @Bean
    public DogFactoryBean dog() {
        return new DogFactoryBean(); // 此處可加強
    }
}
