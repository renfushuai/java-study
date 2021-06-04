package com.rfs.javastudy.modules.study.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
* @author: rfs
* @create: 2021/6/3
* @description: 具体的主题
**/
public class ConcreteSubject implements Subject {
    // 订阅者容器
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
