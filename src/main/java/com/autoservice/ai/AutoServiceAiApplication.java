package com.autoservice.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutoServiceAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoServiceAiApplication.class, args);
    }
}
