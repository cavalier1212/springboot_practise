package com.example.bean.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
        // 用id查找，沒id會以全路徑加#編號
        Object bookService = ctx.getBean("bookService");
        System.out.println(bookService);
        // 取得所有bean的名稱
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println("bean name : "+name);
        }
    }
}
