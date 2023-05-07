package com.example.springboot_05_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot_05_test.domain.Book;
import com.example.springboot_05_test.domain.BookCase;
import com.example.springboot_05_test.service.impl.BookServiceImpl;

@SpringBootTest
// 加Transactional，資料庫的操作會自動rollback
@Transactional
// 可不加，預設Rollback true，
@Rollback(true)
public class ServiceTest {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private BookCase bookCase;

    @Test
    void testService() throws Exception {
        System.out.println("1111");
    }

    @Test
    void testBookService() throws Exception {
        Book book = new Book();
        book.setDescription("SpringBoot1");
        book.setName("SpringBook1");
        book.setType("SpringBoot1");
        bookService.save(book);
    }

    @Test
    void testBookEntity() throws Exception {
        System.out.println(bookCase);
    }
}
