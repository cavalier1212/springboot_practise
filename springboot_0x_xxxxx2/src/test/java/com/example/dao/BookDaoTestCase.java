package com.example.dao;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Book;

@SpringBootTest
public class BookDaoTestCase {
	
	@Autowired
	private BookDao bookDao;

	@Test
	void contextLoads() {
		bookDao.selectById(1);
	}
	
	@Test
	void testGetAll() {
		bookDao.selectList(null);
	}

	@Test
	void testInsert() {
		Book book = new Book();
		book.setName("槍砲彈藥");
		book.setType("Non-fiction");
		book.setDescription("A book for Knowledge");
		bookDao.insert(book);
	}

	@Test
	void testDelete() {
		bookDao.deleteById(-1451450366);
	}

	@Test
	void testUpdate(){
		Book book = new Book();
		book.setId(-985829375);
		book.setName("槍砲與彈藥");
		book.setType("Non-fiction");
		book.setDescription("A book for Knowledge");
		bookDao.updateById(book);
	}

	@Test
	void testPage(){
		// IPage 需定義 interceptor 才能生效
		IPage<Book> page = new Page<Book>(2,5);
		bookDao.selectPage(page, null);

		System.out.println(page.getCurrent());
		System.out.println(page.getSize());
		System.out.println(page.getPages());
		System.out.println(page.getTotal());
		System.out.println(page.getRecords());
	}

	@Test
	void testGetBy(){
		QueryWrapper<Book> qw = new QueryWrapper<Book>();
		qw.like("name", "中");
		bookDao.selectList(qw);
	}
	
	@Test
	void testGetBy2(){
		String queryStr = "中";
		LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<Book>();
		// Strings.isNotEmpty(queryStr) 才加 where 條件
		qw.like(Strings.isNotEmpty(queryStr),Book::getName, queryStr);
		bookDao.selectList(qw);
	}
}
