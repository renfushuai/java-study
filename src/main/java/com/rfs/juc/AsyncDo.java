package com.rfs.juc;

import cn.hutool.http.HttpUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AsyncDo {
    @Async("getTaskExecutor")
    public void doTaskThree(int i) throws Exception {
        TimeUnit.MILLISECONDS.sleep(100);
        String result1= HttpUtil.post("https://passport.csdn.net/v1/api/add/homeWordCount", "{\"id\":"+i+"}");
        System.out.println(result1);
    }

}
