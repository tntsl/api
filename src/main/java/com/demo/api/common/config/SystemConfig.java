package com.demo.api.common.config;

import com.demo.api.common.domain.SystemInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfig {

    @Bean
    @ConfigurationProperties(prefix = "api")
    public SystemInfo api() {
        return new SystemInfo();
    }
}
