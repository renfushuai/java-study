package com.rfs.javastudy.demo.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author: rfs
 * @create: 2021/4/16
 * @description: 信号量
 **/
public class SemaphoreTest {
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println("欢迎 "+Thread.currentThread().getName()+" 来到停车场");
                if (semaphore.availablePermits()==0){
                    System.out.println("车位不足，请稍等");
                }
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 进入停车场");
                    Thread.sleep(new Random().nextInt(10000));
                    System.out.println(Thread.currentThread().getName()+" 驶出停车场");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
