package com.rfs.dto;

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
    private Date birthday;
}
