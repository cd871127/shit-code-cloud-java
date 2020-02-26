package com.shit_code.cloud.lib.springboot.database.sql;

import com.shit_code.cloud.lib.springboot.database.sql.handler.SimpleScriptHandler;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
@Configuration
@EnableConfigurationProperties({SqlScriptProperties.class, FlywayProperties.class})
@ConditionalOnClass(Flyway.class)
public class SqlScriptAutoConfiguration {
    @Bean
    SimpleScriptHandler simpleScriptHandler(SqlScriptProperties sqlScriptProperties, FlywayProperties flywayProperties) {
        SimpleScriptHandler simpleScriptHandler = new SimpleScriptHandler();
        simpleScriptHandler.setFlywayProperties(flywayProperties);
        simpleScriptHandler.setSqlScriptProperties(sqlScriptProperties);
        return simpleScriptHandler;
    }
}
