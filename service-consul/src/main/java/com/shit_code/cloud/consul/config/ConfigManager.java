package com.shit_code.cloud.consul.config;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.shit_code.cloud.consul.config.configuration.Config;
import com.shit_code.cloud.consul.config.configuration.ConfigProperties;
import com.shit_code.cloud.consul.config.loader.ConfigLoader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Anthony Chen
 * @date 2020/3/5
 **/
@Component
public class ConfigManager {

    @Resource
    private ConsulClient consulClient;

    @Resource
    private ConfigProperties configProperties;

    private final List<ConfigLoader> configLoaders;

    public ConfigManager(List<ConfigLoader> configLoaders) {
        this.configLoaders = configLoaders;
    }

    /**
     * clean and load
     *
     * @return
     */
    public void reload(String applicationName) {
        clean(applicationName);
        load(applicationName, false);
    }
//  dev/application/files

    /**
     * @param overwrite 是否覆盖
     * @return 加载配置
     */
    public void load(String applicationName, boolean overwrite) {
        PutParams putParams = null;
        QueryParams queryParams = null;
        String token = null;
        Stream<Config> stream = configLoaders.stream()
                .flatMap(configLoader -> configLoader.loadConfig().stream())
                .distinct();
        //不覆盖就需要过滤已有的key
        if (!overwrite) {
            String keyPrefix = "";
            String separator = null;
            Response<List<String>> consulResponse = consulClient.getKVKeysOnly(keyPrefix, separator, token, queryParams);
            stream = stream.filter(config -> !consulResponse.getValue().contains(config.getKey()));
        }

        stream.forEach(config ->
                consulClient.setKVValue(config.getKey(), config.getValue(), token, putParams, queryParams)
        );
    }

    public void clean(String applicationName) {
        String keyPrefix = "";
        String separator = null;
        String token = null;
        QueryParams queryParams = null;
        Response<List<String>> consulResponse = consulClient.getKVKeysOnly(keyPrefix, separator, token, queryParams);
        consulResponse.getValue().parallelStream().forEach(key -> consulClient.deleteKVValue(key, token, queryParams));
    }


}
