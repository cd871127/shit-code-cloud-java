package com.shit_code.cloud.consul.config;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/17
 **/
@Component
public class ConfigUploader {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 上传配置
     *
     * @param configContents
     */
    public void uploadConfig(List<ConfigContent> configContents) {
        configContents.forEach(configContent -> {
            restTemplate.put("/v1/kv/" + configContent.getPath().toString().replaceAll("\\\\", "/"), configContent.getContent());
        });
    }


}
