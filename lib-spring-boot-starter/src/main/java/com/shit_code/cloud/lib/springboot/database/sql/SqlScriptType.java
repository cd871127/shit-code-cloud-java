package com.shit_code.cloud.lib.springboot.database.sql;

import com.shit_code.cloud.lib.springboot.database.sql.generator.MultiDbTableGenerator;
import com.shit_code.cloud.lib.springboot.database.sql.generator.SqlScriptGenerator;
import com.shit_code.cloud.lib.springboot.database.sql.generator.YearDayGenerator;
import com.shit_code.cloud.lib.springboot.database.sql.generator.YearMonthGenerator;

/**
 * @author Anthony Chen
 * @date 2020/2/24
 **/
public enum SqlScriptType {
    /**
     * 多库多表
     */
    MULTI_DB_MULTI_TABLE(new MultiDbTableGenerator()),
    /**
     * 年库天表
     */
    YEAR_DB_DAY_TABLE(new YearDayGenerator()),
    /**
     * 年库月表
     */
    YEAR_DB_MONTH_TABLE(new YearMonthGenerator());

    SqlScriptType(SqlScriptGenerator sqlScriptGenerator) {
        this.sqlScriptGenerator = sqlScriptGenerator;
    }

    private SqlScriptGenerator sqlScriptGenerator;

    public SqlScriptGenerator getSqlScriptGenerator() {
        return sqlScriptGenerator;
    }
}
