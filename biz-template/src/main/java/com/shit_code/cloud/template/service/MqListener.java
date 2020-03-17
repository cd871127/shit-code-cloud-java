package com.shit_code.cloud.template.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.shit_code.cloud.template.configration.TemplateConfiguration.DIRECT_QUEUE_NAME;

@Component
@Slf4j
@RabbitListener(queues = DIRECT_QUEUE_NAME)
public class MqListener {
    @RabbitHandler
    public void process(String message) {
        log.info("receive message: {}", message);
    }
}
