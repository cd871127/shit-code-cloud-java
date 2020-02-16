package com.shit_code.cloud.consul.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Anthony Chen
 * @date 2020/2/17
 **/
@Data
@Component
@ConfigurationProperties(prefix = "shitcode.consul.request", ignoreInvalidFields = true)
public class RestTemplateProperties {
    private String url;

    private Integer port;
}
