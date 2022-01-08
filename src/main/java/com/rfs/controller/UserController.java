package com.rfs.controller;

import com.alibaba.fastjson.JSON;
import com.rfs.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renfushuai
 * @date 2021/9/14
 */
@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("/t1")
    public String longToDateTest(@RequestBody UserDto userDto)
    {
        return JSON.toJSONString(userDto);
    }
}
