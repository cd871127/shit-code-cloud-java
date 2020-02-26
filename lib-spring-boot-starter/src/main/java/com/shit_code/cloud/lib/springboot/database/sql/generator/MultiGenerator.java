package com.shit_code.cloud.lib.springboot.database.sql.generator;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.util.ArrayList;

/**
 * 多库多表sql文件生成
 *
 * @author Anthony Chen
 * @date 2020/2/26
 **/
public class MultiGenerator extends AbstractSqlScriptGenerator {

    @Override
    protected void handleSingleScript(SqlScript sqlScript) {

        sqlScript.setGeneratedContents(new ArrayList<>(sqlScript.getDbNum() * sqlScript.getTableNum()));
        for (int dbNum = 0; dbNum < sqlScript.getDbNum(); ++dbNum) {
            for (int tableNum = 0; tableNum < sqlScript.getTableNum(); ++tableNum) {
                sqlScript.getGeneratedContents().add(sqlScript.getOriginContent()
                        .replaceAll("\\$\\{dbNum}", biDigitFormat.format(dbNum))
                        .replaceAll("\\$\\{tableNum}", biDigitFormat.format(tableNum)));
            }
        }
    }
}
