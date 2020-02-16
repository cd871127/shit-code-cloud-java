package com.shit_code.cloud.consul.config.loader;

import com.shit_code.cloud.consul.config.ConfigContent;

import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
public interface ConfigLoader {
    /**
     * 加载配置
     *
     * @return 配置实体
     */
    List<ConfigContent> loadConfig();
}
