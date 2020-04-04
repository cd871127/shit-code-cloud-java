package com.shit_code.cloud.lib.springboot.log;

import com.shit_code.cloud.lib.springboot.log.aspect.MonitorLogAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Anthony Chen
 * @date 2020/2/20
 **/
@Configuration
@ConditionalOnClass({ProceedingJoinPoint.class})
public class LogAutoConfiguration {
//    @Bean
//    public MonitorLogAspect monitorLogAspect() {
//        return new MonitorLogAspect();
//    }
}
