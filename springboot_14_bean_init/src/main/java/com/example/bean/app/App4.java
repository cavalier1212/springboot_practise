package com.example.bean.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.bean.Dog;
import com.example.bean.config.SpringConfig4;

public class App4 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig4.class);
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println("bean name : "+name);
        }
        System.out.println("----------------------");
        System.out.println(ctx.getBean(Dog.class));
    }
}
