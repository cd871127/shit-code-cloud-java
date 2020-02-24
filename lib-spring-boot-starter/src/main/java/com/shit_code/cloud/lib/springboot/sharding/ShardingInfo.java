package com.shit_code.cloud.lib.springboot.sharding;

import lombok.Data;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
@Data
public class ShardingInfo {
    /**
     * db数量
     */
    private Integer dbNum = 10;
    /**
     * 每个db多少表
     */
    private Integer tableNum = 10;

    /**
     * db名称
     */
    private String dbName;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 占位符前缀
     */
    private String placeholderPrefix = "${";
    /**
     * 占位符后缀
     */
    private String placeholderSuffix = "}";

    /**
     * 分库分表类型
     */
    private ShardingType type = ShardingType.MULTI_DB_MULTI_TABLE;
}
