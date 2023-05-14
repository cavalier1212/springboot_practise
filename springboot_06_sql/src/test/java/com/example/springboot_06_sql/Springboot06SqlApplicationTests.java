package com.example.springboot_06_sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.example.springboot_06_sql.dao.BookDao;
import com.example.springboot_06_sql.domain.Book;


@SpringBootTest
class Springboot06SqlApplicationTests {

	@Autowired
	private BookDao bookDao;

	@Test
	void contextLoads() {
		bookDao.selectById(1);
	}

	@Test
	void testJDBCTemplates(@Autowired JdbcTemplate jdbcTemplate) {
		String sql = "select * from book";
		RowMapper<Book> rm = new RowMapper<Book>(){

			@Override
			@Nullable
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setDescription(rs.getString("description"));
				book.setType(rs.getString("type"));
				return book;
			}
			
		};
		List<Book> list = jdbcTemplate.query(sql, rm);
		System.out.println(list);
	}

	@Test
	void testJDBCTemplatesSave(@Autowired JdbcTemplate jdbcTemplate) {
		String sql = "insert into book(id,name,description,type) values(9999,'springboot11','springboot22','springboot33')";
		int update = jdbcTemplate.update(sql);
		System.out.println(update);
	}
}
