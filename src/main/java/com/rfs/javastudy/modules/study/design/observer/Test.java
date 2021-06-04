package com.rfs.javastudy.modules.study.design.observer;

public class Test {
    public static void main(String[] args) {
        ConcreteSubject concreteSubject=new ConcreteSubject();
        concreteSubject.attach(new FriendOneObserver());
        FriendTwoObserver friendTwoObserver = new FriendTwoObserver();
        concreteSubject.attach(friendTwoObserver);
        concreteSubject.notifyObservers("第一个朋友圈消息");
        concreteSubject.detach(friendTwoObserver);
        concreteSubject.notifyObservers("第二个朋友圈消息");
    }
}
