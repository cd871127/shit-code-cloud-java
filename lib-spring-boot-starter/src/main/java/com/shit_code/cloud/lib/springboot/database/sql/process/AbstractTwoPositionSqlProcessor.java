package com.shit_code.cloud.lib.springboot.database.sql.process;

import lombok.NoArgsConstructor;

/**
 * @author Anthony Chen
 * @date 2020/2/27
 **/
@NoArgsConstructor
public abstract class AbstractTwoPositionSqlProcessor extends AbstractSqlProcessor {
    public AbstractTwoPositionSqlProcessor(AbstractSqlProcessor abstractSqlProcessor) {
        super(abstractSqlProcessor);
    }

    @Override
    protected String pattern() {
        return "00";
    }
}
