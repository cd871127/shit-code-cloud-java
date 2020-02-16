package com.shit_code.cloud.consul.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.shit_code.cloud.consul.config.Constants.LOCAL_CONFIG_PREFIX;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
@Data
@Component
@ConfigurationProperties(prefix = LOCAL_CONFIG_PREFIX, ignoreInvalidFields = true)
public class LocalConfigProperties {
    String path;

    List<String> suffix;
}
