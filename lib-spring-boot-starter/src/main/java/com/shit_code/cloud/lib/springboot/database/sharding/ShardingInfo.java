package com.shit_code.cloud.lib.springboot.database.sharding;

import com.shit_code.cloud.lib.springboot.database.sharding.strategy.ShardingStrategy;
import com.shit_code.cloud.lib.springboot.database.sql.SqlScriptType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 分库分表信息
 *
 * @author Anthony Chen
 * @date 2020/2/24
 **/
@Data
@NoArgsConstructor
public class ShardingInfo {
    public ShardingInfo(SqlScriptType sqlScriptType) {
        setType(sqlScriptType);
    }

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
    /**
     * 分库分表类型
     */
    private SqlScriptType type = SqlScriptType.MULTI_DB_MULTI_TABLE;

    private Integer year = LocalDate.now().getYear();
    private Integer month = LocalDate.now().getMonthValue();
    private Integer day = LocalDate.now().getDayOfMonth();
}
