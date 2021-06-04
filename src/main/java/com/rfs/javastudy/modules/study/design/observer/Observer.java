package com.rfs.javastudy.modules.study.design.observer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
* @author: rfs
* @create: 2021/6/3
* @description: 订阅者
 * 什么是观察者模式？他的目的是什么？
 * 当一个对象的状态发生改变时，已经登记的其他对象能够观察到这一改变从而作出自己相对应的改变。通过这种方式来达到减少依赖关系，解耦合的作用。
**/
public interface Observer {
    // 处理业务逻辑
    void update(String message);
}
