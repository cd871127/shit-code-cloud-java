package com.shit_code.cloud.consul.config.configuration;

import com.shit_code.cloud.consul.config.loader.LocalConfigLoader;
import com.shit_code.cloud.consul.config.properties.LocalConfigProperties;
import com.shit_code.cloud.consul.config.properties.RestTemplateProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static com.shit_code.cloud.consul.config.Constants.LOCAL_CONFIG_PREFIX;

/**
 * @author Anthony Chen
 * @date 2020/2/17
 **/
@Configuration
public class ConfigConfiguration {

    @Bean
    RestTemplate restTemplate(RestTemplateProperties restTemplateProperties) {
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(10)).setReadTimeout(Duration.ofSeconds(30))
                .uriTemplateHandler(new RootUriTemplateHandler(String.format("http://%s:%d", restTemplateProperties.getUrl(), restTemplateProperties.getPort())))
                .build();
    }

    @Bean
    @ConditionalOnProperty(value = {"path"}, prefix = LOCAL_CONFIG_PREFIX)
    LocalConfigLoader localConfigLoader(LocalConfigProperties localConfigProperties) {
        return new LocalConfigLoader(localConfigProperties);
    }

}
