package com.shit_code.cloud.consul.config.loader;


import com.shit_code.cloud.consul.config.configuration.Config;

import java.util.Collections;
import java.util.List;

/**
 * 从数据库加载配置
 *
 * @author Anthony Chen
 * @date 2020/2/16
 **/
public class DatabaseConfigLoader implements ConfigLoader {
    @Override
    public List<Config> loadConfig() {
        return Collections.emptyList();
    }
}
