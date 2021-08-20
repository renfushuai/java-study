package com.rfs.javastudy.redundantcode.reflection;

import java.lang.annotation.*;

/**
* @author: rfs
* @create: 2021/8/20
* @description: 我们定义一个接口 API 的注解 BankAPI，包含接口 URL 地址和接口说明
**/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
public @interface BackAPI {
    String desc() default "";
    String url() default "";
}
