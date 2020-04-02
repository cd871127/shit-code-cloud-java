package com.shit_code.cloud.template.service;


        import lombok.extern.slf4j.Slf4j;
        import org.springframework.amqp.core.AmqpTemplate;
        import org.springframework.data.redis.core.RedisTemplate;
        import org.springframework.scheduling.annotation.Async;
        import org.springframework.stereotype.Service;

        import javax.annotation.Resource;
        import java.time.Duration;

        import static com.shit_code.cloud.template.configration.TemplateConfiguration.DIRECT_EXCHANGE;
        import static com.shit_code.cloud.template.configration.TemplateConfiguration.DIRECT_ROUTE_KEY;

@Slf4j
@Service
public class TemplateService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private AmqpTemplate amqpTemplate;

    public void setCache(String key, String value, long expiry) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMillis(expiry));
    }

    public String getCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void sendMessage(String message) {
        amqpTemplate.convertAndSend(DIRECT_EXCHANGE, DIRECT_ROUTE_KEY, message);
    }

    @Async
    public void threadLog() {
        log.info("async traceId");
    }
}
