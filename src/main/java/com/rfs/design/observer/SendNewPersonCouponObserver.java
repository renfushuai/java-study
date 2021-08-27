package com.rfs.design.observer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: rfs
 * @create: 2021/6/4
 * @description: 新人注册发送优惠券类
 **/
public class SendNewPersonCouponObserver implements Observer {
    int corePoolSize = 2;
    int maximumPoolSize = 4;
    long keepAliveTime = 10;
    TimeUnit unit = TimeUnit.SECONDS;
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
    ThreadFactory threadFactory = new NameTreadFactory();
    RejectedExecutionHandler handler = new MyIgnorePolicy();
    ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
            workQueue, threadFactory, handler);

    @Override
    public void update(String message) {
        Future<String> future = pool.submit(() -> {
                    String userNme = "";
                    //远程调用发优惠券服务
                    return "调用发券服务，返回结果 ok";
                }
        );
        try {
            System.out.println(future.get(200, TimeUnit.MILLISECONDS));
        } catch (Exception e) {

        }

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("发送新人优惠券");
        }).start();
        System.out.println("执行异步返回");
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }
}
