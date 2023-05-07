package com.example.springboot_05_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MsgConfig {
    
    @Bean
    public String msg(){
        return "msg test";
    }
}
