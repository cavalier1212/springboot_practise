package com.example.bean.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.bean.config.SpringConfig3;

public class App3 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig3.class);
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println("bean name : "+name);
        }
        System.out.println(ctx.getBean("dog"));
    }
}
