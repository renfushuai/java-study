package com.rfs.design.singleton;

/**
 * @author: rfs
 * @create: 2021/4/15
 * @description: 双重加锁
 **/
public class DoubleLockSingleton {
    private DoubleLockSingleton() {}
    private static volatile DoubleLockSingleton instance;
    public static DoubleLockSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleLockSingleton.class) {
                if (instance == null) {
                    instance = new DoubleLockSingleton();
                }
            }
        }
        return instance;
    }
}
