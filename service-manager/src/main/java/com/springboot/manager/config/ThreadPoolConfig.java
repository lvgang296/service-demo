package com.springboot.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     * Set the ThreadPoolExecutor's core pool size.
     */
    @Value("${thread-pool.initPoolSize}")
    private int corePoolSize;
    /**
     * Set the ThreadPoolExecutor's maximum pool size.
     */
    @Value("${thread-pool.maxPoolSize}")
    private int maxPoolSize;
    /**
     * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     */
    @Value("${thread-pool.queneCapatity}")
    private int queueCapacity;

    @Bean
    public Executor customerExecutor () {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("customer-executor");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行（异步变同步）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}