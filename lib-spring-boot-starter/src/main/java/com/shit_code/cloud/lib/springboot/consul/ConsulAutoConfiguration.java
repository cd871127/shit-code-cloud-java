package com.shit_code.cloud.lib.springboot.consul;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
@ConditionalOnBean({ConsulClient.class})
@Configuration
public class ConsulAutoConfiguration {
    @Resource
    private ConsulClient consulClient;

    @Bean
    ConsulLock consulLock() {
        return new ConsulLock(consulClient);
    }
}
