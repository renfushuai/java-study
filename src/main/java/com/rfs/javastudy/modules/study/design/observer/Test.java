package com.rfs.javastudy.modules.study.design.observer;

import com.alibaba.fastjson.JSON;

public class Test {
    public static void main(String[] args) {
        ConcreteSubject concreteSubject=new ConcreteSubject();
        concreteSubject.attach(new FriendOneObserver());
        FriendTwoObserver friendTwoObserver = new FriendTwoObserver();
        concreteSubject.attach(friendTwoObserver);
        concreteSubject.notifyObservers("第一个朋友圈消息");
        concreteSubject.detach(friendTwoObserver);
        concreteSubject.notifyObservers("第二个朋友圈消息");

        byte[] bs = JSON.toJSONString("任富帅").getBytes();
        System.out.println(String.valueOf(bs));
        System.out.println(new String(bs));
    }
}
