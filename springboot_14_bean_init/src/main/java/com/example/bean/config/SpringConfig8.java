package com.example.bean.config;

import org.springframework.context.annotation.Import;

import com.example.bean.MyImportBeanDefinitionRegistrar;
import com.example.bean.MyImportBeanDefinitionRegistrar2;
import com.example.bean.MyPostProcessor;
import com.example.bean.service.impl.BookServiceImpl1;


// MyPostProcessor最後動作
// ImportBeanDefinitionRegistrar加載順序大於BookServiceImpl1.class
// 如果有兩個 ImportBeanDefinitionRegistrar 則後蓋前
@Import({MyImportBeanDefinitionRegistrar2.class,MyImportBeanDefinitionRegistrar.class, BookServiceImpl1.class, MyPostProcessor.class})
public class SpringConfig8 {
}
