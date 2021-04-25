package com.rfs.javastudy;

import com.rfs.javastudy.modules.study.design.abstractfactory.EnumShareType;
import com.rfs.javastudy.modules.study.design.abstractfactory.Share;
import com.rfs.javastudy.modules.study.design.abstractfactory.ShareFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
class JavaStudyApplicationTests {
    @Autowired
    private ShareFactory shareFactory;
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Share shareFunction = shareFactory.getShareFunction(EnumShareType.SUCCESS_ORDER);
        String success_order = shareFunction.mainProcess("success order");
        System.out.println(success_order);
    }

}
