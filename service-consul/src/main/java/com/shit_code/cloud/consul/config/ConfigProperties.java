package com.shit_code.cloud.consul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.shit_code.cloud.consul.Constants.CONFIG_PREFIX;

/**
 * @author Anthony Chen
 * @date 2020/2/17
 **/
@Data
@Component
@ConfigurationProperties(prefix = CONFIG_PREFIX, ignoreInvalidFields = true)
public class ConfigProperties {

    private LocalConfig local;

    private DatabaseConfig database;

    private GitConfig gitConfig;

    @Data
    public static class LocalConfig {
        String path;

        List<String> suffix;
    }

    @Data
    public static class DatabaseConfig {
    }

    @Data
    public static class GitConfig {
    }
}
