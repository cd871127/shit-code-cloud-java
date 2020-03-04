package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

/**
 * @author Anthony Chen
 * @date 2020/3/4
 **/
public interface SqlProcessor {
    /**
     * 处理sql
     *
     * @param sqlScript
     * @return
     */
    void process(SqlScript sqlScript);

    /**
     * 设置下一个
     *
     * @param sqlProcessor
     */
    void setNextSqlProcessor(SqlProcessor sqlProcessor);
}
