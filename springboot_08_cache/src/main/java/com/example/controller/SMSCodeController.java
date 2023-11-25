package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.SMSCodeService;

@RestController
@RequestMapping("/sms")
public class SMSCodeController {
    
    @Autowired
    private SMSCodeService smsCodeService;

    @GetMapping("/send")
    public String send(String phone) {
        return smsCodeService.sendCode(phone);
    }

    @PostMapping("/check")
    public boolean check(String phone,String code) {
        return smsCodeService.checkCode(phone, code);
    }
}
