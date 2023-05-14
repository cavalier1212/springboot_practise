package com.example.springboot_07_redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class Springboot07RedisApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void set() {
		ValueOperations ops = redisTemplate.opsForValue();
		ops.set("name", "Alix3");
	}

	@Test
	void get() {
		ValueOperations ops = redisTemplate.opsForValue();
		Object object = ops.get("name");
		System.out.println("result = ");
		System.out.println(object);
	}


	@Test
	void hSet() {
		HashOperations ops = redisTemplate.opsForHash();
		ops.put("info", "a", "a11");
	}

	@Test
	void hGet() {
		HashOperations ops = redisTemplate.opsForHash();
		Object object = ops.get("info", "a");
		System.out.println(object);
	}
}
