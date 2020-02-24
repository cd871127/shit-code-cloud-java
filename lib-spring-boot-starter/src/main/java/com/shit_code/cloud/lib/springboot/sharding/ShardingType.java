package com.shit_code.cloud.lib.springboot.sharding;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
public enum ShardingType {
    /**
     * 单库多表
     */
    SINGLE_DB_MULTI_TABLE,
    /**
     * 多库多表
     */
    MULTI_DB_MULTI_TABLE,
    /**
     * 年库天表
     */
    YEAR_DB_DAY_TABLE,
    /**
     * 年库月表
     */
    YEAR_DB_MONTH_TABLE;
}
