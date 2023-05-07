package com.example.springboot_05_test.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
// 配置文件推薦使用 kebab-case
@ConfigurationProperties(prefix ="test-case.book")
public class BookCase {
    private int id;
    private int id2;
    private int id3;
    private String name;
    private String description;
}
