package com.rfs.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author renfushuai
 * @date 2021/12/28
 *  一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序开始执行");
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i=0;i<5;i++){
            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程id结束:"+Thread.currentThread().getId());
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("所有线程执行结束");
    }
}
