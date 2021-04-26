package com.rfs.javastudy.config.thead;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @author: rfs
* @create: 2021/4/25
* @description: 线程池配置
**/
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class AsyncTaskConfiguration {
    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveSeconds;
    private int queueCapacity;
}
