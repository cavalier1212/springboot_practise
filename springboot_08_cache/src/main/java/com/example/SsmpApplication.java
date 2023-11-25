package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// 開啟cache功能，使用@Cacheable、@CachePut、@CacheEvict、@CacheConfig等注解实现缓存功能。
@EnableCaching
public class SsmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsmpApplication.class, args);
	}

}
 