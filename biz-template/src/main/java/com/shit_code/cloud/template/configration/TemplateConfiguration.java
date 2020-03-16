package com.shit_code.cloud.template.configration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class TemplateConfiguration {
    public static final String DIRECT_EXCHANGE = "TemplateDirectExchange";
    public static final String DIRECT_ROUTE_KEY = "DirectKey";
    public static final String DIRECT_QUEUE_NAME = "TemplateDirectQueue";

    @Bean
    public Queue queue() {
        // 声明队列 参数一：队列名称；参数二：是否持久化
        return new Queue(DIRECT_QUEUE_NAME, false);
    }

    // 配置默认的交换机，以下部分都可以不配置，不设置使用默认交换器（AMQP default）
    @Bean
    DirectExchange directExchange() {
        // 参数一：交换器名称；参数二：是否持久化；参数三：是否自动删除消息
        return new DirectExchange(DIRECT_EXCHANGE, false, false);
    }

    // 绑定“direct”队列到上面配置的“mydirect”路由器
    @Bean
    Binding bindingExchangeDirectQueue(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with(DIRECT_ROUTE_KEY);
    }


    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("test");

        // 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
