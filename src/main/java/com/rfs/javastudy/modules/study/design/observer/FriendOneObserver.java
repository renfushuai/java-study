package com.rfs.javastudy.modules.study.design.observer;

/**
* @author: rfs
* @create: 2021/6/3
* @description: 第一个朋友观察者类
**/
public class FriendOneObserver implements Observer{
    @Override
    public void update(String message) {
        System.out.println("friendOne知道你发动态了："+message);
    }
}
