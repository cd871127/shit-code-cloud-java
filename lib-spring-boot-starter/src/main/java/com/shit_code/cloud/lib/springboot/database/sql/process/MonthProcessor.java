package com.shit_code.cloud.lib.springboot.database.sql.process;

import lombok.NoArgsConstructor;

/**
 * @author Anthony Chen
 * @date 2020/2/27
 **/
@NoArgsConstructor
public class MonthProcessor extends AbstractTwoPositionSqlProcessor {
    public MonthProcessor(AbstractSqlProcessor abstractSqlProcessor) {
        super(abstractSqlProcessor);
    }



    @Override
    protected String placeholder() {
        return null;
    }
}
