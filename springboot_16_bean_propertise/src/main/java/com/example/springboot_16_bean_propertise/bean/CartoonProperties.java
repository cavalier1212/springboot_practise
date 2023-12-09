package com.example.springboot_16_bean_propertise.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

// @Component // 改為時呼叫誰幫此類加載成Component
@ConfigurationProperties(prefix = "cartoon") // 是Bean 才能讀配置
@Data
public class CartoonProperties {
    private Cat cat;
    private Mouse mouse; 
}
