package com.rfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@SpringBootApplication(scanBasePackages = "com.rfs")
@EnableScheduling
@EnableAsync
public class JavaStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaStudyApplication.class, args);
        ApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        Object ms = annotationConfigWebApplicationContext.getBean("ms");

    }
}
