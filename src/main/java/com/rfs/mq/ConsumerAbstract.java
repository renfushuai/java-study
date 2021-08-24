package com.rfs.mq;

import com.rfs.utils.XLoggerUtil;
import lombok.Data;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
* @author: rfs
* @create: 2021/4/15
* @description: 消费者模板类，所有消费者继承模板
**/
public abstract class ConsumerAbstract {

    protected abstract MessageListenerConcurrently listener();

    protected abstract ConsumerConfig getConsumerConfig();

    @Value("${rmq.nameserver}")
    private String nameserver;
    @PostConstruct
    public void start() throws MQClientException {
        ConsumerConfig config = getConsumerConfig();
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(config.group);
        consumer.setNamesrvAddr(nameserver);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(config.topic,config.tags);
        consumer.setPullBatchSize(config.batchSize);
        consumer.setConsumeThreadMin(config.threadMin);
        consumer.setConsumeThreadMax(config.threadMax);
        consumer.registerMessageListener(listener());
        XLoggerUtil.info("consumer start success, topic is:{"+config.topic+"}, group is:{"+ config.group+"}");
        consumer.start();
    }

    @Data
    class ConsumerConfig {
        private String topic;
        private String group;
        private String tags = "*";
        /**
         * 默认单条消费
         */
        private Integer batchSize = 1;
        private Integer threadMin = 10;
        private Integer threadMax = 20;
    }
}
