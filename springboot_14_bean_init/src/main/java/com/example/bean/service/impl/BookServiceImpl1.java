package com.example.bean.service.impl;

import org.springframework.stereotype.Service;

import com.example.bean.service.BookService;

@Service("bookService")
public class BookServiceImpl1 implements BookService {

    @Override
    public void check() {
        System.out.println("book service impl 1 .");
    }

}
