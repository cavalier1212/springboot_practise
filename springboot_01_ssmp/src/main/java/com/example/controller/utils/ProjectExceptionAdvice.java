package com.example.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    
    // handle all exception
    @ExceptionHandler(Exception.class)
    public R handleException(Exception exception) {
        // 通知相關人員、印LOG
        return new R("維護中，請稍後再試。");
    }
}
