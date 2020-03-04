package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

/**
 * @author Anthony Chen
 * @date 2020/3/4
 **/
public class DayProcessor extends AbstractRangeReplaceProcessor {

    @Override
    protected int startIndex(SqlScript sqlScript) {
        return sqlScript.getSharding().getDay();
    }

    @Override
    protected int endIndex(SqlScript sqlScript) {
        return 32;
    }

    @Override
    protected String replaceFlag() {
        return "${day}";
    }

    @Override
    protected String placeHolder() {
        return "\\$\\{day}";
    }
}
