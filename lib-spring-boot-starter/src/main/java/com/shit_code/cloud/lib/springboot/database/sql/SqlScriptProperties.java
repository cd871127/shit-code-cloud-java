package com.shit_code.cloud.lib.springboot.database.sql;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
@Data
@ConfigurationProperties(prefix = "shitcode.flyway")
public class SqlScriptProperties {
    /**
     * 原始脚本位置
     */
    private String sourceLocation;
    /**
     * 目标脚本位置
     */
    private String targetLocation;
    /**
     * 占位符前缀
     */
    private String placeholderPrefix = "${";
    /**
     * 占位符后缀
     */
    private String placeholderSuffix = "}";
    /**
     * 脚本文件后缀
     */
    private String scriptSuffix;
    /**
     * 脚本信息
     */
    private Map<String, SqlScript> scripts;

}
