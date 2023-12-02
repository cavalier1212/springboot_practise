package com.example.actuator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class InfoConfig implements InfoContributor {

    @Override
    public void contribute(Builder builder) {
        builder.withDetail("buildTime", 2023);
        Map<String, Object> details = new HashMap<>();
        details.put("runTime", System.currentTimeMillis());
        details.put("date", new Date().toString());
        builder.withDetails(details);
    }

}
