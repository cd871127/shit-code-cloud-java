package com.shit_code.cloud.lib.springboot.database.sql.process;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/27
 **/
@NoArgsConstructor
public class NormalProcessor extends AbstractSqlProcessor {

    @Override
    public String process(String sql, List<Integer> args) {
        return sql;
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
