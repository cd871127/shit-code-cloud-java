package com.shit_code.cloud.consul.config.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.shit_code.cloud.consul.config.configuration.Constants.CONFIG_PREFIX;


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

    private GitConfig git;

    private String env = "";

    private String consulRoot = "config";

    @Data
    public static class LocalConfig {
        private String path;

        private List<String> suffix;

    }

    @Data
    public static class DatabaseConfig {

    }

    @Data
    public static class GitConfig {

    }
}
