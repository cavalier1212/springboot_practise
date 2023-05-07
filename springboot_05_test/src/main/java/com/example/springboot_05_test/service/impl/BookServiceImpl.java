package com.example.springboot_05_test.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot_05_test.dao.BookDao;
import com.example.springboot_05_test.domain.Book;
import com.example.springboot_05_test.service.IBookService;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {
    
}
