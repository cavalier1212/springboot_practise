package com.example.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class HealthConfig extends AbstractHealthIndicator{

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        boolean condition = true;
        if(condition){
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("buildTime","2006");
            builder.withDetail("runTime",System.currentTimeMillis());
            builder.withDetail("company","boring");
            builder.withDetails (infoMap);
            builder.status(Status.UP);
         }else{
            builder. status (Status.DOWN);
         }
    }

}
