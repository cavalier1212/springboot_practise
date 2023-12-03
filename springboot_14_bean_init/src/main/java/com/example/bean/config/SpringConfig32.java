package com.example.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.example.bean.DogFactoryBean;

@Configuration
@ImportResource("applicationContext1.xml")
public class SpringConfig32 {

    // DogFactoryBean 返回 Dog
    @Bean
    public DogFactoryBean dog() {
        return new DogFactoryBean(); // 此處可加強
    }
}
