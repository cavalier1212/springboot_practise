package com.example.springboot_05_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

// 啟動Web環境
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 開啟虛擬MVC調用
@AutoConfigureMockMvc
public class WebTest {
    
    @Test
    void testRandomPort() throws Exception {}

    @Test
    // 注入虛擬MVC調用物件
    void testWeb(@Autowired MockMvc mvc) throws Exception{
        // 創建虛擬請求，訪問books
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        // 執行請求
        mvc.perform(builder);
    }

    @Test
    void testStatus(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions action = mvc.perform(builder);

        // 狀態
        StatusResultMatchers status = MockMvcResultMatchers.status();
        // 預期狀態
        ResultMatcher ok = status.isOk();
        // 比對狀態，不符會拋出斷言異常
        action.andExpect(ok);
    }

    @Test
    void testBody(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions action = mvc.perform(builder);

        // 設定預期
        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher bodyString = content.string("SpringBoot Running...");
        
        // 比對body，不符會拋出斷言異常
        action.andExpect(bodyString);
    }

    @Test
    void testJson(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions action = mvc.perform(builder);

        // 設定預期
        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher jsonString = content.json("{\"id\":1,\"type\":\"SpringBoot2\",\"name\":\"SpringBook1\",\"description\":\"SpringBoot1\"}");
        
        // 比對body，不符會拋出斷言異常
        action.andExpect(jsonString);
    }
    

    @Test
    void testContentType(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions action = mvc.perform(builder);

        // 設定預期
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher contentType = header.string("Content-Type", "application/json");
        
        // 比對body，不符會拋出斷言異常
        action.andExpect(contentType);
    }

    // 正式測試
    @Test
    void testGetBookById(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions action = mvc.perform(builder);

        // 設定預期
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        // ResultMatcher contentType = header.string("Content-Type", "application/json");

        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher jsonString = content.json("{\"id\":1,\"type\":\"SpringBoot1\",\"name\":\"SpringBook1\",\"description\":\"SpringBoot1\"}");

        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher ok = status.isOk();
        
        // 比對body，不符會拋出斷言異常
        action.andExpect(ok).andExpect(content.contentType(MediaType.APPLICATION_JSON)).andExpect(jsonString);
    }
}
