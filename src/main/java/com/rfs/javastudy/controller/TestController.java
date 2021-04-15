package com.rfs.javastudy.controller;

import com.rfs.javastudy.utils.XLoggerUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/index")
    public String index(){
        XLoggerUtil.info("测试日志");
        return "123";
    }
}
