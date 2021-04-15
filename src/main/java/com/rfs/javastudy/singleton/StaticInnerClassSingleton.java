package com.rfs.javastudy.singleton;

/**
* @author: rfs
* @create: 2021/4/15
* @description: 静态内部类
**/
public class StaticInnerClassSingleton {
    private  StaticInnerClassSingleton(){}
    private static class SingletonHolder{
        public static final StaticInnerClassSingleton INSTANCE=new StaticInnerClassSingleton();
    }
    public static StaticInnerClassSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
