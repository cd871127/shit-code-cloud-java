package com.shit_code.cloud.lib.springboot.flyway;

import com.shit_code.cloud.lib.springboot.flyway.handler.SqlFileHandler;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
@Configuration
@EnableConfigurationProperties(FlyWayProperties.class)
@ConditionalOnClass(Flyway.class)
public class FlyWayAutoConfiguration {
    @Bean
    SqlFileHandler sqlFileHandler() {
//        return new SqlFileHandler();
        return null;
    }
}
