package com.example.bean.config;

import org.springframework.context.annotation.Import;

import com.example.bean.MyImportSelector;


@Import(MyImportSelector.class)
public class SpringConfig6 {
}
