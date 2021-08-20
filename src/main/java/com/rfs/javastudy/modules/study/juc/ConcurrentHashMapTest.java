package com.rfs.javastudy.modules.study.juc;

import cn.hutool.core.date.StopWatch;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class ConcurrentHashMapTest {
    private static int THREAD_COUNT = 10;
    private static int ITEM_COUNT = 1000;

    private static ConcurrentHashMap<String, Long> getData(int count) {
        ConcurrentHashMap<String, Long> collect = LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(), (o1, o2) -> o2, ConcurrentHashMap::new));
        return collect;
    }

    public void putConcurrentHashMap() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
//        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
//            // 如果不加锁，putAll 这样的聚合方法也不能确保原子性，在 putAll 的过程中去获取数据可能会获取到部分数据。
//            //诸如 size、isEmpty 和 containsValue 等聚合方法，在并发情况下可能会反映 ConcurrentHashMap 的中间状态。因此在并发情况下，这些方法的返回值只能用作参考，而不能用于流程控制。显然，利用 size 方法计算差异值，是一个流程控制。
//            //synchronized (concurrentHashMap) {
//            System.out.println(Thread.currentThread().getName());
//            //查询还需要补充多少个元素
//                int gap = ITEM_COUNT - concurrentHashMap.size();//不能判断当前的个数
//                System.out.println("gap size:" + gap);
//                concurrentHashMap.putAll(getData(gap));
//           // }
//        }));
        IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
                    // 如果不加锁，putAll 这样的聚合方法也不能确保原子性，在 putAll 的过程中去获取数据可能会获取到部分数据。
                    //诸如 size、isEmpty 和 containsValue 等聚合方法，在并发情况下可能会反映 ConcurrentHashMap 的中间状态。因此在并发情况下，这些方法的返回值只能用作参考，而不能用于流程控制。显然，利用 size 方法计算差异值，是一个流程控制。
                    //synchronized (concurrentHashMap) {
                    System.out.println(Thread.currentThread().getName());
                    //查询还需要补充多少个元素
                    int gap = ITEM_COUNT - concurrentHashMap.size();//不能判断当前的个数
                    System.out.println("gap size:" + gap);
                    concurrentHashMap.putAll(getData(gap));
                    // }
                });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("finish size:" + concurrentHashMap.size());
    }

    //使用 Map 来统计 Key 出现次数的场景吧
    private static Map<String, Long> normaluse() throws InterruptedException {
        ConcurrentHashMap<String,Long> freqs=new ConcurrentHashMap<>(10);
        ForkJoinPool forkJoinPool=new ForkJoinPool(10);
        forkJoinPool.execute(()->{
            IntStream.rangeClosed(1,10000000).parallel().forEach(i->{
                String key="item"+ ThreadLocalRandom.current().nextInt(10);
               synchronized (freqs){
                    if (freqs.containsKey(key)){
                        freqs.put(key,freqs.get(key)+1);
                    }else {
                        freqs.put(key,1L);
                    }
                }
            });
        });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1,TimeUnit.HOURS);
        return freqs;
    }
    //使用 Map 来统计 Key 出现次数的场景吧
    private static Map<String, Long> gooduse() throws InterruptedException {
        ConcurrentHashMap<String,LongAdder> freqs=new ConcurrentHashMap<>(10);
        ForkJoinPool forkJoinPool=new ForkJoinPool(10);
        forkJoinPool.execute(()->{
            IntStream.rangeClosed(1,10000000).parallel().forEach(i->{
                String key="item"+ ThreadLocalRandom.current().nextInt(10);
                freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
            });
        });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1,TimeUnit.HOURS);
        return freqs.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().longValue()));
    }
    public static void main(String[] args) throws InterruptedException {
        new ConcurrentHashMapTest().putConcurrentHashMap();
        /*StopWatch st=new StopWatch("使用 Map 来统计 Key 出现次数");
        st.start("开始");
        normaluse();
        //gooduse();
        st.stop();
        System.out.println(st.getTotalTimeMillis());*/
    }
}
