package com.rfs.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author renfushuai
 * @date 2021/9/14
 */
@Data
public class UserDto {
    private Integer id;
    private String name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
