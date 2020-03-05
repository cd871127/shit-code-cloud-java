package com.shit_code.cloud.lib.springboot.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Anthony Chen
 * @date 2020/2/20
 **/
@Slf4j
@Configuration
@ConditionalOnBean({RedisTemplate.class})
@EnableConfigurationProperties(RedisProperties.class)
@Import({RedisTemplate.class})
public class ShitCodeRedisAutoConfiguration {
//    /**
//     * 用json的序列化器替换掉默认的session序列化器
//     *
//     * @return
//     */
//    @Bean
//    @ConditionalOnClass({GenericJackson2JsonRedisSerializer.class, RedisSerializer.class})
//    public RedisSerializer springSessionDefaultRedisSerializer() {
//        log.info("Init jsonSerializer for session");
//        return new GenericJackson2JsonRedisSerializer();
//    }
//
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(
//            RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setDefaultSerializer(new GenericFastJsonRedisSerializer());
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }

    @Bean

    public RedisLock redisLock(RedisTemplate<String, String> redisTemplate) {
        return new RedisLock(redisTemplate);
    }

}
