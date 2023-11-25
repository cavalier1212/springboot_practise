package com.example.service;

public interface SMSCodeService {
    public String sendCode(String phone);
    public boolean checkCode(String phone,String code);
}
