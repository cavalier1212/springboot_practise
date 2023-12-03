package com.example.bean.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.bean.config.SpringConfig32;

public class App32 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig32.class);
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println("bean name : "+name);
        }
        System.out.println(ctx.getBean("dog"));
    }
}
