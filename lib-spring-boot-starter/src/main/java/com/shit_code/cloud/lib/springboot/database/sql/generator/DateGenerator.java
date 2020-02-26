package com.shit_code.cloud.lib.springboot.database.sql.generator;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.util.ArrayList;

/**
 * @author Anthony Chen
 * @date 2020/2/26
 **/
public class DateGenerator extends AbstractSqlScriptGenerator {
    @Override
    protected void handleSingleScript(SqlScript sqlScript) {
        sqlScript.setGeneratedContents(new ArrayList<>());
        for (int year = sqlScript.getYear(); year <= sqlScript.getYear() + 2; ++year) {

        }
    }
}
