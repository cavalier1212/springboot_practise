package com.example.spring_0x_sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public String getById() {
        log.debug("debug..");
        log.info("info..");
        log.warn("warn...");
        log.error("error...");
        return "SpringBoot Running...";
    }
}
