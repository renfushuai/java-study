package com.rfs.javastudy;

import com.rfs.javastudy.demo.design.abstractfactory.EnumShareType;
import com.rfs.javastudy.demo.design.abstractfactory.Share;
import com.rfs.javastudy.demo.design.abstractfactory.ShareFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rfs")
public class JavaStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaStudyApplication.class, args);
    }
}
