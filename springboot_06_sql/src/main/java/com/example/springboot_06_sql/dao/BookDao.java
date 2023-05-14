package com.example.springboot_06_sql.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_06_sql.domain.Book;

@Mapper
public interface BookDao extends BaseMapper<Book> {

}
