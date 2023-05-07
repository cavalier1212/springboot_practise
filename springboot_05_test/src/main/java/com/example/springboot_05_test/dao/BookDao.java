package com.example.springboot_05_test.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_05_test.domain.Book;

@Mapper
public interface BookDao extends BaseMapper<Book> {

}
