package com.rfs.javastudy.demo.design.abstractfactory;

/**
* @author: rfs
* @create: 2021/4/20
* @description: 分享类型
**/
public enum EnumShareType {
    SUCCESS_ORDER("successOrder");
    EnumShareType(String name){
        this.name=name;
    }
    private String name;
}
