package com.example.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.bean.Cat;


// proxyBeanMethods = true(預設) 拿到的bean對象會是初始創建的同一個
// proxyBeanMethods = false 拿到的bean對象 每次都是新的
@Configuration(proxyBeanMethods = true)
public class SpringConfig33 {

    @Bean
    public Cat cat() {
        return new Cat();
    }
}
