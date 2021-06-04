package com.rfs.javastudy.modules.study.design.observer;

/**
* @author: rfs
* @create: 2021/6/3
* @description: 主题接口
**/
public interface Subject {
    // 添加订阅关系
    void attach(Observer observer);
    // 移除订阅关系
    void detach(Observer observer);
    // 通知订阅者
    void notifyObservers(String message);
}
