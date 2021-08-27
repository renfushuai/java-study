package com.rfs.thread;
/**
* @author: rfs
* @create: 2021/6/10
* @description: thread.join() 方法会让 B 一直等待直到 A 运行完毕。
**/
public class JoinTest {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }, "A");
        a.start();
        Thread b = new Thread(() -> {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }, "B");
        b.start();
    }
}
