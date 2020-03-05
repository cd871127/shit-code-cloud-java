package com.shit_code.cloud.consul.config;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.shit_code.cloud.consul.config.configuration.Config;
import com.shit_code.cloud.consul.config.configuration.ConfigProperties;
import com.shit_code.cloud.consul.config.loader.ConfigLoader;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
    public void reloadConfig() {
        cleanConfig();
        pushConfig(false);
    }

    /**
     * @param overwrite 是否覆盖
     * @return 加载配置
     */
    public void pushConfig(boolean overwrite) {
        PutParams putParams = null;
        QueryParams queryParams = null;
        String token = null;
        String keyPrefix = getKeyPrefix();
        Stream<Config> stream = configLoaders.stream()
                .flatMap(configLoader -> configLoader.loadConfig().stream()).distinct()
                .filter(config -> config.getEnv().contains(configProperties.getEnv()));

        //不覆盖就需要过滤已有的key
        if (!overwrite) {
            String separator = null;
            Response<List<String>> consulResponse = consulClient.getKVKeysOnly(keyPrefix, separator, token, queryParams);
            if (consulResponse != null && CollectionUtils.isNotEmpty(consulResponse.getValue())) {
                Set<String> valueSet = consulResponse.getValue().parallelStream().collect(Collectors.toSet());
                stream = stream.filter(config -> !valueSet.contains(configProperties.getConsulRoot() + "/" + config.getKey()));
            }
        }

        stream.forEach(config ->
                consulClient.setKVValue(configProperties.getConsulRoot() + "/" + config.getKey(), config.getValue(), token, putParams, queryParams)
        );
    }

    public void cleanConfig() {
        String keyPrefix = getKeyPrefix();
        String separator = null;
        String token = null;
        QueryParams queryParams = null;
        consulClient.deleteKVValues(keyPrefix, token, queryParams);
//        Response<List<String>> consulResponse = consulClient.getKVKeysOnly(keyPrefix, separator, token, queryParams);
//        consulResponse.getValue().parallelStream().forEach(key -> consulClient.deleteKVValue(key, token, queryParams));
    }

    private String getKeyPrefix() {
        return configProperties.getConsulRoot() + "/" + configProperties.getEnv();
    }

}
