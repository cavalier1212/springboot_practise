package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.controller.utils.CodeUtils;
import com.example.service.SMSCodeService;

@Service
public class SMSCodeServiceImpl implements SMSCodeService{

    @Autowired
    private CodeUtils codeUtils;

    @Autowired
    private SMSCodeServiceImpl self;

    @Override
    @CachePut(value = "smsCode", key = "#phone" ) // 只會放入緩存
    public String sendCode(String phone) {
        return codeUtils.generator(phone);
    }

    @Override
    public boolean checkCode(String phone, String code) {
        //self 自我注入 實現 spring代理，直接呼叫不會被spring所有接管
        String cacheCode = self.getCode(phone);
        System.err.println(cacheCode);
        return code.equals(cacheCode);
    }
    
    // @Cacheable 需要被spring代理調用，class內直接呼叫不會有反應
    @Cacheable(value = "smsCode", key = "#phone" )
    public String getCode(String phone) {
        return null;
    }
}
