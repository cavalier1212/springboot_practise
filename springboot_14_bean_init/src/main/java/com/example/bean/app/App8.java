package com.example.bean.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.bean.config.SpringConfig8;
import com.example.bean.service.BookService;

public class App8 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig8.class);
        System.out.println("----------------------");
        BookService bookService = ctx.getBean("bookService",BookService.class);
        bookService.check();
    }
}
