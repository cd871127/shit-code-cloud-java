package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

/**
 * @author Anthony Chen
 * @date 2020/3/4
 **/
public class TableNumProcessor extends AbstractRangeReplaceProcessor {


    @Override
    protected int startIndex(SqlScript sqlScript) {
        return 0;
    }

    @Override
    protected int endIndex(SqlScript sqlScript) {
        return sqlScript.getSharding().getTableNum();
    }

    @Override
    protected String placeHolder() {
        return "\\$\\{tableNum}";
    }
}
