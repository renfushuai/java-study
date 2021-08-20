package com.rfs.javastudy.modules.study.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author: rfs
 * @create: 2021/6/9
 * @description: volatile Test
 * println方法是由synchronized关键字修饰的，
 * synchronized和IO操作都是比较重量级的操作，让线程有机会放弃CPU的使用权，并且有机会去拉去主内存中的变量。
 **/
public class VolatileTest {
    // 这里还没有加volatile关键字
    private static volatile boolean flag = true;
    public static void main(String[] args) throws Exception{
        new Thread(() -> {
            while (flag) {
                //当我不加下面的这句话时，这个线程将一直循环，不会感知flag变化
                //加了之后，线程可以感知flag的变化，及时退出
                //System.out.println();
            }
            System.out.println("循环结束flag:"+flag);
        }).start();
        TimeUnit.SECONDS.sleep(2);
        flag = false;
    }
}
