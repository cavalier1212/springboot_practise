package com.example.springboot_05_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.springboot_05_test.config.MsgConfig;

@SpringBootTest
// 導入測試類臨時專用配置
@Import(MsgConfig.class)
public class ConfigurationTest {

    @Autowired
    private String msg;
    
    @Test
    void testMsg(){
        System.out.println(msg);
    }
}
