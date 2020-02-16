package com.shit_code.cloud.consul.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigContent {
    /**
     * 配置内容
     */
    private String content;

    /**
     * consul路径
     */
    private Path path;

}
