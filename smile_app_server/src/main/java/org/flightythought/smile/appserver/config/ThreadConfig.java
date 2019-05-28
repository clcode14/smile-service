package org.flightythought.smile.appserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ThreadConfig
 * @CreateTime 2019/5/27 18:26
 * @Description: TODO
 */
@Configuration
@EnableAsync
public class ThreadConfig {
    /**
     * 执行需要依赖线程池，这里就来配置一个线程池
     */
    @Bean
    public Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(250);
        executor.initialize();
        return executor;
    }
}
