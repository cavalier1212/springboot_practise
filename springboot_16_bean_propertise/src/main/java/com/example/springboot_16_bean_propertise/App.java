package com.example.springboot_16_bean_propertise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import bean.CartoonCatAndMouse;

@SpringBootApplication
@Import(CartoonCatAndMouse.class)
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);;
		CartoonCatAndMouse cartoon = ctx.getBean(CartoonCatAndMouse.class);
		cartoon.run();
	}
}
