package com.shit_code.cloud.lib.springboot.database.sharding;

import com.shit_code.cloud.lib.springboot.database.sharding.strategy.ShardingStrategyFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Anthony Chen
 * @date 2020/2/25
 **/
@ConditionalOnProperty(prefix = "shitcode.sharding")
@EnableConfigurationProperties(ShardingProperties.class)
public class ShardingAutoConfiguration {
    @Bean
    ShardingStrategyFactory shardingStrategyFactory() {
        return null;
    }
}
