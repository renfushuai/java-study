package com.rfs.javastudy.demo.design.singleton;

import java.lang.reflect.Constructor;

/**
* @author: rfs
* @create: 2021/4/15
* @description: 饿汉式
**/
public class HungrySingleton {
    private HungrySingleton(){}
    private static HungrySingleton instance=new HungrySingleton();
    public static HungrySingleton getInstance(){
        return instance;
    }

    public static void main(String[] args) throws Exception {
        HungrySingleton singleton=HungrySingleton.getInstance();
        Constructor<HungrySingleton> declaredConstructor = HungrySingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        HungrySingleton singleton1 = declaredConstructor.newInstance();
        HungrySingleton singleton2=HungrySingleton.getInstance();
        System.out.println(singleton2==singleton);
    }
}
