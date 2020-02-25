package com.shit_code.cloud.lib.springboot.database.sharding;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author Anthony Chen
 * @date 2020/2/25
 **/
@Data
@ConfigurationProperties(prefix = "shitcode.sharding")
public class ShardingProperties {
    private Map<String, ShardingInfo> shardingInfos;
}
