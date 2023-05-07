package com.example.springboot_05_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

// Spring Boot 2.4.0 及以上版本，properties 參數的優先權就會高於 args 參數
@SpringBootTest(properties = {"test.prop=testValue1"}, args = {"--test.prop=testValue2"})
public class PropertiesAndargsTest {


    @Value("${test.prop}")
    private String msg;
    
    @Test
    void testPropertiesAndArgs(){
        System.out.println("msg = "+msg);
    }
}
