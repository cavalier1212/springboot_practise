package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Book;

// mp service interface  IService擴充
public interface IBookService extends IService<Book>{
    IPage<Book> getPage(int currentPAge, int pageSize );
    IPage<Book> getPage(int currentPAge, int pageSize, Book book);
}
