package com.shit_code.cloud.lib.springboot.database.sql.generator;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Anthony Chen
 * @date 2020/2/26
 **/
public abstract class YearDbSqlScriptGenerator extends AbstractSqlScriptGenerator {

    protected DecimalFormat decimalFormat = new DecimalFormat("00");

    @Override
    protected void handleSingleScript(SqlScript sqlScript) {
        sqlScript.setGeneratedContents(new ArrayList<>());
        for (int year = sqlScript.getYear(); year < sqlScript.getYear() + 2; ++year) {
            handleDate(sqlScript, year);
        }
    }

    /**
     * 处理月或者日
     *
     * @param script
     * @param year
     */
    protected abstract void handleDate(SqlScript script, int year);
}
