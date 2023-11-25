package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
public class MPConfig {
    
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        // 定義
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
