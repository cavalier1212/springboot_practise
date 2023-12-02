package com.example.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="pay")
public class PayEndPoint {
    @ReadOperation
    public Object getPay (){
    //调用业务操作，获取这付相关信息结果，最终return出去
    Map<String, Integer> payMap = new HashMap<>();
    payMap.put("level 1", 103); 
    payMap.put("level 2",315); 
    payMap.put("level 3" ,666); return payMap;
    }
}
