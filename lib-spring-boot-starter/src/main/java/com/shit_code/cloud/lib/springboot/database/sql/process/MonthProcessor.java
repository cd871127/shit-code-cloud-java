package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

/**
 * @author Anthony Chen
 * @date 2020/3/4
 **/
public class MonthProcessor extends AbstractRangeReplaceProcessor {

    @Override
    protected int startIndex(SqlScript sqlScript) {
        return sqlScript.getSharding().getMonth();
    }

    @Override
    protected int endIndex(SqlScript sqlScript) {
        return 13;
    }

    @Override
    protected String replaceFlag() {
        return "${month}";
    }

    @Override
    protected String placeHolder() {
        return "\\$\\{month}";
    }
}
