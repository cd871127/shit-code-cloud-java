package com.shit_code.cloud.lib.springboot.database.sql;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
@Configuration
@EnableConfigurationProperties(SqlScriptProperties.class)
@ConditionalOnClass(Flyway.class)
public class SqlScriptAutoConfiguration {

}
