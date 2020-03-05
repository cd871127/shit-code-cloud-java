package com.shit_code.cloud.consul.config.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.Objects;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Config {

    /**
     * 配置内容
     */
    private String value;


    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置环境
     */
    private String env;

    public String getKey() {
        return env + "/" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Config that = (Config) o;
        return getKey().equals(that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }

    public static class ConfigFactory {
        public static Config createConfig(Path path, String value) {
            if (path == null) {
                return null;
            }
            Path env = path.getParent();
            if (env == null) {
                return null;
            }
            return Config.builder().env(env.getFileName().toString())
                    .name(path.getFileName().toString())
                    .value(value).build();
        }
    }
}
