package com.shit_code.cloud.consul.config.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
@Data
@NoArgsConstructor
public class Config {

    public Config(String key, String value) {
        this();
        this.key = key;
        this.value = value;
    }

    /**
     * 配置内容
     */
    private String value;

    /**
     * consul路径
     */
    private String key;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Config that = (Config) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
