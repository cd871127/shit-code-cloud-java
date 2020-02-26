package com.shit_code.cloud.lib.springboot.database.sql.process;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Anthony Chen
 * @date 2020/2/27
 **/

@NoArgsConstructor
public class DayProcessor extends AbstractTwoPositionSqlProcessor {
    public DayProcessor(AbstractSqlProcessor abstractSqlProcessor) {
        super(abstractSqlProcessor);
    }

    @Override
    protected boolean support(String sql) {
        return false;
    }


    @Override
    protected String placeholder() {
        return null;
    }
}
