package com.shit_code.cloud.consul.config.configuration;


import com.shit_code.cloud.consul.config.loader.DatabaseConfigLoader;
import com.shit_code.cloud.consul.config.loader.GitConfigLoader;
import com.shit_code.cloud.consul.config.loader.LocalConfigLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author Anthony Chen
 * @date 2020/2/17
 **/
@EnableConfigurationProperties({ConfigProperties.class})
@Configuration
public class ConfigConfiguration {

    @Bean
    @Order(10)
    @ConditionalOnProperty(prefix = Constants.CONFIG_PREFIX, value = "local.enable", havingValue = "true", matchIfMissing = true)
    LocalConfigLoader localConfigLoader(ConfigProperties configProperties) {
        return new LocalConfigLoader(configProperties);
    }

    @Bean
    @Order(20)
    DatabaseConfigLoader databaseConfigLoader(ConfigProperties configProperties) {
        return new DatabaseConfigLoader();
    }

    @Bean
    @Order(30)
    GitConfigLoader gitConfigLoader(ConfigProperties configProperties) {
        return new GitConfigLoader();
    }
}
