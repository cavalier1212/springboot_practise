package com.example.springboot_04_configuration.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "servers")
@Validated
public class ServerConfig {
    private String ipAddress;
    @Max(value = 8888, message = "最大值不能超過8888")
    @Min(value = 202, message = "最小值不能小於202")
    private int port;
    private long timeout;
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration serverTimeout;
    // @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;
}
