package com.shit_code.cloud.consul.manager.configuration;

import com.shit_code.cloud.consul.manager.configuration.properties.RestTemplateProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author Anthony Chen
 * @date 2020/2/17
 **/
@Configuration
public class ConsulConfiguration {

    @Bean
    RestTemplate restTemplate(RestTemplateProperties restTemplateProperties) {
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(10)).setReadTimeout(Duration.ofSeconds(30))
                .uriTemplateHandler(new RootUriTemplateHandler(String.format("http://%s:%d", restTemplateProperties.getUrl(), restTemplateProperties.getPort())))
                .build();
    }



}
