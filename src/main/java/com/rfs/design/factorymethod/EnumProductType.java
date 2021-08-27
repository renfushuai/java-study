package com.rfs.design.factorymethod;

/**
* @author: rfs
* @create: 2021/4/20
* @description: 商品类型枚举
**/

public enum EnumProductType {
    activityOne("one"),
    activityTwo("two");
    private String name;
    EnumProductType(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

}
