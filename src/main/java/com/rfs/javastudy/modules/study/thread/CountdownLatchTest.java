package com.rfs.javastudy.modules.study.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
* @author: rfs
* @create: 2021/6/10
* @description: CountdownLatchTest
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
