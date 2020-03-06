package com.shit_code.cloud.consul.config;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.shit_code.cloud.consul.config.configuration.Config;
import com.shit_code.cloud.consul.config.configuration.ConfigProperties;
import com.shit_code.cloud.consul.config.loader.ConfigLoader;
import com.shit_code.cloud.consul.config.writer.ConfigWriter;
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

    private final List<ConfigWriter> configWriters;

    public ConfigManager(List<ConfigLoader> configLoaders, List<ConfigWriter> configWriters) {
        this.configLoaders = configLoaders;
        this.configWriters = configWriters;
    }

    /**
     * clean and load
     *
     * @return
     */
    public void reloadConfig() {
        cleanConfig();
        pushConfig();
    }

    public void pushConfig() {
        pushConfig(true);
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

    /**
     * 清空配置
     */
    public void cleanConfig() {
        String keyPrefix = getKeyPrefix();
        String token = null;
        QueryParams queryParams = null;
        consulClient.deleteKVValues(keyPrefix, token, queryParams);
    }

    /**
     * 回写配置
     */
    public void writeBack() {
        String token = null;
        QueryParams queryParams = null;
        Response<List<GetValue>> kvs = consulClient.getKVValues(getKeyPrefix(), token, queryParams);
        if (kvs == null || CollectionUtils.isEmpty(kvs.getValue())) {
            return;
        }
        configWriters.parallelStream().forEach(configWriter -> configWriter.write(kvs.getValue()));
    }

    private String getKeyPrefix() {
        return configProperties.getConsulRoot() + "/" + configProperties.getEnv();
    }

}
