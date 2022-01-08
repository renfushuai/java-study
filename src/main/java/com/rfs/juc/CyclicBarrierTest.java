package com.rfs.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author renfushuai
 * @date 2021/12/28
 * 可以看到所有线程在其他线程没有准备好之前都在被阻塞中,等到所有线程都准备好了才继续执行
 * 我们在创建CyclicBarrier对象时传入了一个方法,当调用CyclicBarrier的await方法后,当前线程会被阻塞等到所有线程都调用了await方法后 调用传入CyclicBarrier的方法,然后让所有的被阻塞的线程一起运行
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("所有玩家已经就绪。。");
        });
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println("玩家id："+Thread.currentThread().getId()+" 准备进入游戏");
                    cyclicBarrier.await();
                    System.out.println("玩家id："+Thread.currentThread().getId()+" 开始游戏");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
