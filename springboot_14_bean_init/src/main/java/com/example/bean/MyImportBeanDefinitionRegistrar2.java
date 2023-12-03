package com.example.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.example.bean.service.impl.BookServiceImpl3;

public class MyImportBeanDefinitionRegistrar2 implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition (BookServiceImpl3.class).getBeanDefinition();
        registry.registerBeanDefinition("bookService", beanDefinition);
	}
}
