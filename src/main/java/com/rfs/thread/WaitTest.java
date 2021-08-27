package com.rfs.thread;

public class WaitTest {
    public static Object object=new Object();
    public static void main(String[] args) {
        new Thread(()->{
           synchronized(object){
               System.out.println("A 拿到锁");
               System.out.println("A 1");
               try {
                   System.out.println("A 进入等待状态，放弃lock的控制权");
                   object.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("A 被唤醒 继续执行");
           }
        },"A").start();
        new Thread(()->{
           synchronized (object){
               System.out.println("B 获得锁");
               System.out.println("B 1");
               object.notify();
           }
        }).start();
    }
}
