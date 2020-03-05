package com.shit_code.cloud.lib.springboot.database.sharding;

import com.shit_code.cloud.lib.springboot.database.sharding.strategy.ShardingStrategy;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分库分表信息
 *
 * @author Anthony Chen
 * @date 2020/2/24
 **/
@Data
@NoArgsConstructor
public class ShardingInfo {

    /**
     * db数量
     */
    private Integer dbNum = 1;
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
     * 分库分表策略
     */
    private Class<ShardingStrategy> shardingStrategyClass;

}
