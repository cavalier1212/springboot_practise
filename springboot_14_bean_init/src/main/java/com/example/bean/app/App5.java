package com.example.bean.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.bean.Cat;
import com.example.bean.Dog;
import com.example.bean.config.SpringConfig5;

public class App5 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig5.class);

        ctx.registerBean("tom",Cat.class, 0);
        ctx.registerBean("tom",Cat.class, 1);
        ctx.registerBean("tom",Cat.class, 2);// 最後一個為主 後蓋前
        
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println("bean name : "+name);
        }

        System.out.println("----------------------");
        System.out.println(ctx.getBean(Dog.class));
        System.out.println(ctx.getBean("tom"));
    }
}
