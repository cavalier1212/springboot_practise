package com.example.spring_0x_sample.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    
    @GetMapping
    public String getById() {
        log.info("info..");
        log.debug("debug..");
        log.warn("warn...");
        log.error("error...");
        return "SpringBoot Running...";
    }
}
