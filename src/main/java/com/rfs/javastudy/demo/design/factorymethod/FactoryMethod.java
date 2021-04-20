package com.rfs.javastudy.demo.design.factorymethod;

/**
* @author: rfs
* @create: 2021/4/20
* @description: 工厂方法
**/
public abstract class FactoryMethod {
    protected abstract Product creatProduct(EnumProductType activity);
    public Product product(EnumProductType activity,String name){
        Product product=creatProduct(activity);
        product.setName(name);
        return product;
    }
}
