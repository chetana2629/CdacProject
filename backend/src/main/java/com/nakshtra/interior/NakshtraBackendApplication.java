package com.nakshtra.interior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages="com.nakshtra.interior")
@EntityScan(basePackages ="com.nakshtra.interior")
@SpringBootApplication(scanBasePackages = "com.nakshtra.interior")
public class NakshtraBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(NakshtraBackendApplication.class, args);
    }
}
