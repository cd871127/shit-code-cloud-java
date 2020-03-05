package com.shit_code.cloud.consul.config;

import com.shit_code.cloud.consul.config.loader.DatabaseConfigLoader;
import com.shit_code.cloud.consul.config.loader.GitConfigLoader;
import com.shit_code.cloud.consul.config.loader.LocalConfigLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import static com.shit_code.cloud.consul.Constants.CONFIG_PREFIX;

/**
 * @author Anthony Chen
 * @date 2020/2/17
 **/
@Configuration
public class ConfigConfiguration {

    /**
     * Order小,在注入的时候会在list前面
     *
     * @param configProperties
     * @return
     */
    @Bean
    @Primary
    @Order(10)
    @ConditionalOnProperty(value = {"local.enabled"}, prefix = CONFIG_PREFIX, havingValue = "true", matchIfMissing = true)
    LocalConfigLoader localConfigLoader(ConfigProperties configProperties) {
        return new LocalConfigLoader(configProperties.getLocal());
    }

    @Bean
    @Order(20)
    @ConditionalOnProperty(value = {"database"}, prefix = CONFIG_PREFIX)
    DatabaseConfigLoader databaseConfigLoader(ConfigProperties configProperties) {
        return new DatabaseConfigLoader();
    }

    @Bean
    @Order(30)
    @ConditionalOnProperty(value = {"git"}, prefix = CONFIG_PREFIX)
    GitConfigLoader gitConfigLoader(ConfigProperties configProperties) {
        return new GitConfigLoader();
    }
}
