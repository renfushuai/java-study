package com.rfs.thread;
/**
* @author: rfs
* @create: 2021/6/10
* @description: thread.join() 方法会让 B 一直等待直到 A 运行完毕。
 * 用Thread.join()只能做到让一个线程执行完之后，做不到同时等待多个线程，比如我们上面的代码，线程1执行完之后才能执行线程2，
 * 无法做到让线程1和线程2同时处理。
**/
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
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

        for (int i = 0; i < 10; i++) {
            Thread t=new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" run ~ ");
            });
            t.start();
            t.join();
        }
        System.out.println("main 线程执行结束");
    }
}
