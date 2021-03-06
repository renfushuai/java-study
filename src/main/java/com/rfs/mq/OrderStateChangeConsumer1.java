package com.rfs.mq;

import com.alibaba.fastjson.JSON;
import com.rfs.constant.ResponseCodeConst;
import com.rfs.utils.XLoggerUtil;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
* @author: rfs
* @create: 2021/4/15
* @description: 订单状态变更消费者
**/
//@Service
public  class OrderStateChangeConsumer1 extends ConsumerAbstract {
    @Value("${rmq.consumer.topics.orderStateChange}")
    private String topic;
    @Value("${rmq.consumer.groupname}")
    private String group;
    private  String tag="1";
    @Override
    protected MessageListenerConcurrently listener() {
        return (msgs, context) -> {
            String message = new String(msgs.get(0).getBody());
            OrderStateChangeMQDto changeContent = JSON.parseObject(message, OrderStateChangeMQDto.class);
            try {
                XLoggerUtil.info("消费组1收到订单状态变更消息， action is:{"+tag+"}, content is:{"+message+"}");
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
