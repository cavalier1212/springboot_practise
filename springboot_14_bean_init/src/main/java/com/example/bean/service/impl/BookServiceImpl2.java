package com.example.bean.service.impl;

import org.springframework.stereotype.Service;

import com.example.bean.service.BookService;

@Service
public class BookServiceImpl2 implements BookService {

    @Override
    public void check() {
        System.out.println("book service impl 2 ... ");
    }

}
