package com.rfs.javastudy.demo.mq;

import com.alibaba.fastjson.JSON;
import com.rfs.javastudy.constant.ResponseCodeConst;
import com.rfs.javastudy.dto.OrderStateChangeMQDto;
import com.rfs.javastudy.utils.XLoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
* @author: rfs
* @create: 2021/4/15
* @description: 订单状态变更消费者
**/
@Service
public  class OrderStateChangeConsumer2 extends ConsumerAbstract {
    @Value("${rmq.consumer.topics.orderStateChange}")
    private String topic;
    @Value("${rmq.consumer.groupname2}")
    private String group;
    private  String tag="2";
    @Override
    protected MessageListenerConcurrently listener() {
        return (msgs, context) -> {
            String message = new String(msgs.get(0).getBody());
            OrderStateChangeMQDto changeContent = JSON.parseObject(message, OrderStateChangeMQDto.class);
            try {
                XLoggerUtil.info("消费组2收到订单状态变更消息， action is:{"+tag+"}, content is:{"+message+"}");
               // processWithAction(changeContent);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }catch (Exception e) {
                XLoggerUtil.error(ResponseCodeConst.CONSUMER_MQ_ERROR,e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        };
    }

    @Override
    protected ConsumerConfig getConsumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTopic(topic);
        consumerConfig.setGroup(group);
        consumerConfig.setTags(tag);
        return consumerConfig;
    }
}
