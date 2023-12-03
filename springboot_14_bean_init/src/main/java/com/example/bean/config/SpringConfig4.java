package com.example.bean.config;

import org.springframework.context.annotation.Import;

import com.example.bean.Dog;


@Import({Dog.class,DBConfig.class})
public class SpringConfig4 {
}
