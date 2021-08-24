package com.rfs.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LogAttr {
    // 文本
    TEXT(0),
    // 输入日志
    INPUT(1),
    // 输出日志
    OUTPUT(2),
    // 时间戳日志
    TIMESTAMP(3),
    // sql 日志（数据操作日志sql、redis等）
    SQL(4),
    // sql 日志（数据操作日志sql、redis等）
    REDIS(5),
    // 调用第三方接口错误
    THIRD_ERR(12);
    private Integer code;

    public Integer getCode() {
        return code;
    }
}
