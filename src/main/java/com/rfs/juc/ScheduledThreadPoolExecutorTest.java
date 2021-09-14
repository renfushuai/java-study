package com.rfs.juc;

import org.apache.commons.validator.Var;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.schedule(()->{
            System.out.println("第一次延迟三秒执行");
        },3, TimeUnit.SECONDS);

        // 返回执行结果
        ScheduledFuture<String> result = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("第二次延迟三秒执行");
            return "执行完成";
        }, 3, TimeUnit.SECONDS);
        System.out.println(result.get());

        // 定时任务，周期性执行 线程启动1s中之后 每2s钟执行一次 如果内部异常 线程不会挂，只是没有新任务提交
        scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{
            System.out.println("发送心跳");
        },1000,2000,TimeUnit.MILLISECONDS);

        //new Timer() 也可以做周期定时任务 单线程，如果内部异常之后，线程会挂掉
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("11111");
            }
        }, 1000, 2000);
    }
}
