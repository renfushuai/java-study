package com.rfs.design.observer;

/**
* @author: rfs
* @create: 2021/6/3
* @description: 第二个朋友观察者类
**/
public class FriendTwoObserver implements Observer{
    @Override
    public void update(String message) {
        System.out.println("friendTwo知道你发动态了："+message);
    }
}
