package com.rfs.javastudy.demo.mq;

import com.alibaba.fastjson.JSON;
import com.rfs.javastudy.dto.OrderStateChangeMQDto;
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
@Slf4j
@Service
public  class OrderStateChangeConsumer2 extends ConsumerAbstract {
    @Value("${rmq.consumer.topics.orderStateChange}")
    private String topic;
    @Value("${rmq.consumer.groupname2}")
    private String group;
    private  String tag="1";
    @Override
    protected MessageListenerConcurrently listener() {
        return (msgs, context) -> {
            String message = new String(msgs.get(0).getBody());
            OrderStateChangeMQDto changeContent = JSON.parseObject(message, OrderStateChangeMQDto.class);
            try {
                log.info("消费组2收到订单状态变更消息， action is:{}, content is:{}", tag, message);
               // processWithAction(changeContent);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }catch (Exception e) {
                log.error("消费组2处理订单状态变更失败，content is:{}", message, e);
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
