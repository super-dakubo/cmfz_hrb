package com.baizhi.conf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableConfigurationProperties(value = AsyncProp.class)
public class AsyncConfig{
    @Autowired
    private AsyncProp asyncProp;
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncProp.corePoolSize);
        executor.setMaxPoolSize(asyncProp.maxPoolSize);
        executor.setQueueCapacity(asyncProp.queueCapacity);
        executor.initialize();
        return executor;
    }
}
