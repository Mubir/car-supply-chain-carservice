package com.mubir.carservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableAsync
//@EnableScheduling
@Configuration
public class TaskConfig { // bring in spring scheduler 
    @Bean
    TaskExecutor taskExecutor()
    {
        return new SimpleAsyncTaskExecutor();
    }
}
