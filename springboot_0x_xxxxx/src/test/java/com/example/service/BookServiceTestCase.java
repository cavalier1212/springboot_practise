package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Book;

@SpringBootTest
public class BookServiceTestCase {

    @Autowired
    private IBookService bookService;
    
    @Test
	void contextLoads() {
		bookService.getById(1);
	}
	
	@Test
	void testGetAll() {
		bookService.list();
	}

	@Test
	void testInsert() {
		Book book = new Book();
		book.setName("槍砲彈藥");
		book.setType("Non-fiction");
		book.setDescription("A book for Knowledge");
		bookService.save(book);
	}

	@Test
	void testDelete() {
		bookService.removeById(968720386);
	}

	@Test
	void testUpdate(){
		Book book = new Book();
		book.setId(1224577025);
		book.setName("槍砲與彈藥");
		book.setType("Non-fiction");
		book.setDescription("A book for Knowledge");
		bookService.updateById(book);
	}

    @Test
	void testPage(){
		// IPage 需定義 interceptor 才能生效
		IPage<Book> page = new Page<Book>(2,5);
		bookService.page(page);

		System.out.println(page.getCurrent());
		System.out.println(page.getSize());
		System.out.println(page.getPages());
		System.out.println(page.getTotal());
		System.out.println(page.getRecords());
	}

}
