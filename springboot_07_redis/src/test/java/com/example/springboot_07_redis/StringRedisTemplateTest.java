package com.example.springboot_07_redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void get(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String name = ops.get("name");
        System.out.println(name);
    }
    
}
