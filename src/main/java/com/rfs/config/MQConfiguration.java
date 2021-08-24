package com.rfs.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class MQConfiguration {
    @Value("${rmq.nameserver}")
    private String nameServer;
    @Value("${rmq.producer.groupname}")
    private String producerGroupName;
    @Value("${rmq.producer.topics.orderStateChange}")
    private String orderStateChangeTopic;
}
