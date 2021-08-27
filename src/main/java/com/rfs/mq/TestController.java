package com.rfs.mq;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.rfs.config.MQConfiguration;
import com.rfs.utils.XLoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RMQProducer rmqProducer;
    @Autowired
    private MQConfiguration mqConfiguration;
    @GetMapping("/index")
    public String index(){
        XLoggerUtil.info("测试日志");
        return "123";
    }
    @GetMapping("/sendmq")
    public OrderStateChangeMQDto sendMq(String tag){
        OrderStateChangeMQDto orderStateChangeMQDto = OrderStateChangeMQDto.builder()
                .orderId(RandomUtil.randomLong(0,100000))
                .sysCode(18)
                .state(1)
                .userId(1L)
                .createTime(DateUtil.date())
                .build();

        rmqProducer.sentMessage(orderStateChangeMQDto,mqConfiguration.getOrderStateChangeTopic(),String.valueOf(orderStateChangeMQDto.getOrderId()),tag);
        return orderStateChangeMQDto;
    }
}
