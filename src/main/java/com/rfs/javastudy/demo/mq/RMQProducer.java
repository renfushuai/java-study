package com.rfs.javastudy.demo.mq;

import com.alibaba.fastjson.JSON;
import com.github.rholder.retry.*;
import com.rfs.javastudy.config.MQConfiguration;
import com.rfs.javastudy.constant.ResponseCodeConst;
import com.rfs.javastudy.exceptions.MQSendFailException;
import com.rfs.javastudy.utils.XLoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


@Service
public class RMQProducer {
    @Autowired
    private MQConfiguration mqConfiguration;
    private DefaultMQProducer producer;

    private Retryer producerRetry = RetryerBuilder.newBuilder()
            .retryIfExceptionOfType(MQSendFailException.class)
            .withWaitStrategy(WaitStrategies.incrementingWait(1L, TimeUnit.SECONDS,1L,TimeUnit.SECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();
    @PostConstruct
    public void init() throws MQClientException {
        producer = new DefaultMQProducer();
        producer.setProducerGroup(mqConfiguration.getProducerGroupName());
        producer.setNamesrvAddr(mqConfiguration.getNameServer());
        producer.start();
        XLoggerUtil.info("rmq producer start finished!, group is :{"+ mqConfiguration.getProducerGroupName()+"}");
    }

    /**
     * 发送消息
     *
     * @param data
     * @param topic
     * @param keys
     * @param <T>
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    public <T> void sentMessage(T data, String topic, String keys, String tags) {
        String content = JSON.toJSONString(data);
        XLoggerUtil.info("发送mq消息, topic is:{"+topic+"}, keys is:{"+keys+"}, data is :{"+content+"}");
        Message message = new Message();
        message.setBody(content.getBytes());
        message.setTopic(topic);
        if (StringUtils.isNotBlank(keys)) {
            message.setKeys(keys);
        }
        if (StringUtils.isNotBlank(tags)) {
            message.setTags(tags);
        }
        try {
            producerRetry.call(() -> {
                sentMessage(message);
                return null;
            });
        } catch (ExecutionException e) {
            throw new RuntimeException("执行重试时发送MQ异常");
        } catch (RetryException e) {
            throw new RuntimeException("执行重试时发送MQ异常");
        }
    }

    public void sentMessageWithRetry(Message message) {
        try {
            producerRetry.call(() -> {
                sentMessage(message);
                return null;
            });
        } catch (ExecutionException e) {
            XLoggerUtil.error( ResponseCodeConst.SEND_MQ_ERROR,e);
            throw new RuntimeException("执行重试时发送MQ异常");
        } catch (RetryException e) {
            XLoggerUtil.error( ResponseCodeConst.SEND_MQ_ERROR,e);
            throw new RuntimeException("执行重试时发送MQ异常");
        }
    }

    public void sentMessage(Message message) {
        SendResult result = null;
        try {
            result = producer.send(message);
        } catch (Exception e) {
            XLoggerUtil.error( ResponseCodeConst.SEND_MQ_ERROR,e);       }
        if (result != null && SendStatus.SEND_OK != result.getSendStatus()) {
            XLoggerUtil.error( ResponseCodeConst.SEND_MQ_ERROR.getMsg(),ResponseCodeConst.SEND_MQ_ERROR.getCode(),String.valueOf(message.getBody()));        }
    }

    public <T> void sentMessage(T data, String topic, String keys) {
        sentMessage(data, topic, keys, null);
    }

    public <T> void sentMessage(T data, String topic) {
        this.sentMessage(data, topic, null);
    }

}