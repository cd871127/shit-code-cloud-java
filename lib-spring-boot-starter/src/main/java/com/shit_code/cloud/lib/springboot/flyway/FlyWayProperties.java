package com.shit_code.cloud.lib.springboot.flyway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
@Data
@ConfigurationProperties(prefix = "shitcode.flyway")
public class FlyWayProperties {
    private String sourceLocation;
    private String targetLocation;
//    private Map<String, ShardingInfo> shardingInfos;
}
