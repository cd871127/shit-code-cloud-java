package com.shit_code.cloud.lib.springboot.database.sql.generator;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Anthony Chen
 * @date 2020/2/26
 **/
public class MultiDbTableGenerator extends AbstractSqlScriptGenerator {

    @Override
    protected void handleSingleScript(SqlScript sqlScript) {
        DecimalFormat df = new DecimalFormat("00");
        sqlScript.setGeneratedContents(new ArrayList<>(sqlScript.getDbNum() * sqlScript.getTableNum()));
        for (int dbNum = 0; dbNum < sqlScript.getDbNum(); ++dbNum) {
            for (int tableNum = 0; tableNum < sqlScript.getTableNum(); ++tableNum) {
                sqlScript.getGeneratedContents().add(sqlScript.getOriginContent()
                        .replaceAll("\\$\\{dbNum}", df.format(dbNum)).replaceAll("\\$\\{tableNum}", df.format(tableNum)));
            }
        }
    }
}
