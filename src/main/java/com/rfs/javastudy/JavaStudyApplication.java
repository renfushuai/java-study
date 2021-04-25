package com.rfs.javastudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rfs")
public class JavaStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaStudyApplication.class, args);
    }
}
