package com.example.springboot_06_sql.domain;

import lombok.Data;

@Data
public class Book {
	private Integer id;
	private String type;
	private String name;
	private String description;
}
