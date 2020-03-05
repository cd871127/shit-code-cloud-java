package com.shit_code.cloud.consul.config.controller;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.shit_code.cloud.consul.config.loader.ConfigLoader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/3/5
 **/
@RestController
@RequestMapping("/config")
public class KeyValueController {
    @Resource
    private ConsulClient consulClient;

    private final List<ConfigLoader> configLoaders;

    /**
     * 构造器注入,不要用Autowired注解
     *
     * @param configLoaders
     */
    public KeyValueController(List<ConfigLoader> configLoaders) {
        this.configLoaders = configLoaders;
    }

    /**
     * @return 刷新配置
     */
    @PutMapping
    String refresh() {
        PutParams putParams = null;
        QueryParams queryParams = null;
        String token = null;
        configLoaders.stream()
                .flatMap(configLoader -> configLoader.loadConfig().stream()).distinct()
                .forEach(config ->
                        consulClient.setKVValue(config.getKey(), config.getValue(), token, putParams, queryParams)
                );
        return "";
    }

    @DeleteMapping
    String clean() {
        String keyPrefix = "";
        String separator = null;
        String token = null;
        QueryParams queryParams = null;
        Response<List<String>> consulResponse = consulClient.getKVKeysOnly(keyPrefix, separator, token, queryParams);
        consulResponse.getValue().parallelStream().forEach(key -> consulClient.deleteKVValue(key, token, queryParams));
        return "";
    }

}
