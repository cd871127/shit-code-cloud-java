package com.shit_code.cloud.lib.springboot.database.sql;

import com.shit_code.cloud.lib.springboot.database.sql.process.*;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
public enum SqlScriptType {
    /**
     * 不分库分表
     */
    NORMAL(new NormalProcessor()),

    /**
     * 多库多表
     */
    MULTI_DB_MULTI_TABLE(new DbNumProcessor(new TableNumProcessor())),

    /**
     * 年库天表
     */
    YEAR_DB_DAY_TABLE(new YearProcessor(new DayProcessor())),

    /**
     * 年库月表
     */
    YEAR_DB_MONTH_TABLE(new YearProcessor(new MonthProcessor()));

    SqlScriptType(AbstractSqlProcessor abstractSqlProcessor) {
        this.abstractSqlProcessor = abstractSqlProcessor;
    }

    private AbstractSqlProcessor abstractSqlProcessor;

    public AbstractSqlProcessor abstractSqlProcessor() {
        return abstractSqlProcessor;
    }
}
