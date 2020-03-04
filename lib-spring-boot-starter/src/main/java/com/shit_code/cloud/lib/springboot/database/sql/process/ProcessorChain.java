package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

/**
 * @author Anthony Chen
 * @date 2020/3/4
 **/
public class ProcessorChain {
    private SqlProcessor sqlProcessor;

    /**
     * 注册处理器
     *
     * @param sqlProcessor
     */
    public ProcessorChain registry(SqlProcessor sqlProcessor) {
        if (this.sqlProcessor != null) {
            this.sqlProcessor.setNextSqlProcessor(sqlProcessor);
        }
        this.sqlProcessor = sqlProcessor;
        return this;
    }

    public void go(SqlScript sqlScript) {
        sqlProcessor.process(sqlScript);
    }

    public static ProcessorChain getDefaultInstance() {
        return new ProcessorChain().registry(new DbNumProcessor()).registry(new TableNumProcessor());
    }
}
