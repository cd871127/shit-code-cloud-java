package com.shit_code.cloud.lib.springboot.database.sql;

import com.shit_code.cloud.lib.springboot.database.sharding.ShardingInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/25
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SqlScript extends ShardingInfo {
    /**
     * 脚本名称
     */
    private String scriptName;
    /**
     * 脚本版本
     */
    private String scriptVersion;
    /**
     * 脚本原始内容
     */
    private String originContent;
    /**
     * 生成的脚本
     */
    private List<String> generatedContents;
}
