package com.rfs.javastudy.modules.study.design.observer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
* @author: rfs
* @create: 2021/6/4
* @description: 注册成功发短信
**/
public class SendSuccessMessageObserver implements Observer{
    @Override
    public void update(String message) {
        System.out.println("发送短信，注册成功，");
    }

    public static void main(String[] args) {
        // 新人注册业务逻辑

        // 通知订阅者
        buildSubject().notifyObservers("");
    }
    private static ConcreteSubject buildSubject(){
        ConcreteSubject concreteSubject = new ConcreteSubject();
        concreteSubject.attach(new SendSuccessMessageObserver());
        concreteSubject.attach(new SendNewPersonCouponObserver());
        return concreteSubject;
    }
}
