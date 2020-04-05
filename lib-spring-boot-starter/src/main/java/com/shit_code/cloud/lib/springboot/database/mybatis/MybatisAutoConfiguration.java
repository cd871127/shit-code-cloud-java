package com.shit_code.cloud.lib.springboot.database.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Anthony Chen
 * @date 2020/2/20
 **/
@Configuration
public class MybatisAutoConfiguration {
    @Bean
    public MybatisLogInterceptor mybatisLogInterceptor() {
        return new MybatisLogInterceptor();
    }
}
