package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

/**
 * @author Anthony Chen
 * @date 2020/3/4
 **/
public class YearProcessor extends AbstractRangeReplaceProcessor {


    @Override
    protected int startIndex(SqlScript sqlScript) {
        return sqlScript.getSharding().getYear();
    }

    @Override
    protected int endIndex(SqlScript sqlScript) {
        return sqlScript.getSharding().getYear() + 1;
    }

    @Override
    protected String replaceFlag() {
        return "${year}";
    }

    @Override
    protected String placeHolder() {
        return "\\$\\{year}";
    }

    @Override
    protected String pattern() {
        return "0000";
    }
}
