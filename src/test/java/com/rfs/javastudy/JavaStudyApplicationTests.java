package com.rfs.javastudy;

import cn.hutool.http.HttpUtil;
import com.rfs.javastudy.modules.study.design.abstractfactory.EnumShareType;
import com.rfs.javastudy.modules.study.design.abstractfactory.Share;
import com.rfs.javastudy.modules.study.design.abstractfactory.ShareFactory;
import com.rfs.javastudy.modules.study.juc.AsyncDo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
class JavaStudyApplicationTests {
    @Autowired
    private ShareFactory shareFactory;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    AsyncDo async;

    @Test
    void contextLoads() {
        Share shareFunction = shareFactory.getShareFunction(EnumShareType.SUCCESS_ORDER);
        String success_order = shareFunction.mainProcess("success order");
        System.out.println(success_order);
    }

    @Test
    void testAsync() throws Exception {
        for (int i = 0; i <= 200000000; i++) {
            async.doTaskThree(i);
        }
    }
    @Test
    void addHello() throws InterruptedException {
        for (int i = 0; i < 10000000; i++) {
            TimeUnit.MILLISECONDS.sleep(100);
            String result1= HttpUtil.post("https://passport.csdn.net/v1/api/add/homeWordCount", "{\"id\":"+i+"}");
            System.out.println(result1);
        }
    }
}
