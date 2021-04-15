package com.rfs.javastudy.singleton;

/**
* @author: rfs
* @create: 2021/4/15
* @description: 懒汉式
**/
public class LazySingleton {
    private LazySingleton(){}
    private static LazySingleton instance;
    public static synchronized LazySingleton getInstance(){
        if (instance==null){
            instance=new LazySingleton();
        }
        return instance;
    }
}
