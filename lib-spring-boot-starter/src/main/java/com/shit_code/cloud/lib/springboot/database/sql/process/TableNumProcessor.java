package com.shit_code.cloud.lib.springboot.database.sql.process;

/**
 * @author Anthony Chen
 * @date 2020/2/27
 **/

public class TableNumProcessor extends AbstractSqlProcessor {
    public TableNumProcessor(AbstractSqlProcessor abstractSqlProcessor) {
        super(abstractSqlProcessor);
    }

    @Override
    protected boolean support(String sql) {
        return false;
    }

    @Override
    protected String pattern() {
        return null;
    }

    @Override
    protected String placeholder() {
        return null;
    }
}
