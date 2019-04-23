package com.baizhi.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "Async")
public class AsyncProp {
    Integer corePoolSize;
    Integer maxPoolSize;
    Integer queueCapacity;
}
