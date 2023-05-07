package com.example.springboot_05_test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_05_test.domain.Book;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/books")
public class BookController {

    // @GetMapping
    // public String getBookById() {
    //     return "SpringBoot Running...";
    // }
    
    @GetMapping
    public Book getBookById() {
        System.out.println("SpringBoot Running...");

        Book book = new Book();
        book.setId(1);
        book.setDescription("SpringBoot1");
        book.setName("SpringBook1");
        book.setType("SpringBoot1");

        return book;
    }
    
}
