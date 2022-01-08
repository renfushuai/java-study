package com.rfs.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
* @author: rfs
* @create: 2021/6/10
* @description: CountdownLatchTest
 * Thread.join()是Thread类的一个方法，而CountDownLatch是JUC包中的一个工具类；
 * Thread.join()的实现是依靠Object的wait()和notifyAll()来完成的，而CountDownLatch是通过AQS完成的；
 * Thread.join()只支持让一个线程等待，不支持同时等待多个线程，而CountDownLatch可以支持，所以CountDownLatch的效率要更高。
**/
public class CountdownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(()->{
            System.out.println("D is waiting for other three threads");
            try {
                countDownLatch.await();
                System.out.println("All thread done,D start working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        for (char threadName='A';threadName<='C';threadName++){
            final String tN=String.valueOf(threadName);
            new Thread(()->{
                System.out.println(tN+ " is running");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(tN+" finished");
                countDownLatch.countDown();
            }).start();
        }
    }
}
