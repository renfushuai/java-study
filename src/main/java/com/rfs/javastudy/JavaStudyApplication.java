package com.rfs.javastudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.rfs")
@EnableScheduling
@EnableAsync
public class JavaStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaStudyApplication.class, args);
    }
}
