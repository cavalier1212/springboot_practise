package com.example.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.controller.utils.R;
import com.example.domain.Book;
import com.example.service.IBookService;


@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private IBookService bookService;
    
    @GetMapping
    public R getAll() {
        return new R(true , bookService.list());
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id) {
        return new R(true , bookService.getById(id));
    }

    @PutMapping
    public R update(@RequestBody Book book) {
        Boolean flag = bookService.updateById(book);
        return new R(flag, flag == true ? "操作成功!" : "操作失敗!");
    }

    @PostMapping
    public R save(@RequestBody Book book) throws IOException {
        if("123".equals(book.getName())) throw new IOException();
        Boolean flag = bookService.save(book);
        return new R(flag, flag == true ? "操作成功!" : "操作失敗!");
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(bookService.removeById(id));
    }

    // @GetMapping("{currentPage}/{pageSize}")
    // public R getPage(@PathVariable int currentPage, @PathVariable int pageSize) {
    //     IPage<Book> page =  bookService.getPage(currentPage,pageSize);
    //     // 當當前頁面 大於 總頁數，用總頁數再去查一次，最大頁數作為當前頁
    //     if (currentPage > page.getPages()) {
    //         page =  bookService.getPage((int)page.getPages(),pageSize);
    //     }
    //     return new R(true,page);
    // }
    
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Book book) {
        IPage<Book> page =  bookService.getPage(currentPage,pageSize, book);
        // 當當前頁面 大於 總頁數，用總頁數再去查一次，最大頁數作為當前頁
        if (currentPage > page.getPages()) {
            page =  bookService.getPage((int)page.getPages(),pageSize, book);
        }
        return new R(true,page);
    }

}

