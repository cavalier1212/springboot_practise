package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dao.BookDao;

@SpringBootTest
class SsmpApplicationTests {

	@Autowired
	private BookDao bookDao;
	
	@Test
	void contextLoads() {
		System.out.println(bookDao.selectById(6));
	}

}
