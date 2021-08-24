package com.rfs.redundantcode.reflection;

import java.lang.annotation.*;

/**
* @author: rfs
* @create: 2021/8/20
* @description: 用于描述接口的每一个字段规范，包含参数的次序、类型和长度三个属性
**/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface BankAPIField {
    int order()default -1;
    int length()default -1;
    BankAPIFieldType type();
}
