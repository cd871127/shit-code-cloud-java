package com.shit_code.cloud.lib.springboot.database.sql.generator;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/26
 **/
public abstract class AbstractSqlScriptGenerator implements SqlScriptGenerator {

    /**
     * 两位数,左边补0
     */
    final protected DecimalFormat biDigitFormat = new DecimalFormat("00");

    @Override
    public void generate(List<SqlScript> scripts) {
        scripts.forEach(this::handleSingleScript);
    }

    /**
     * 处理单个sql
     *
     * @param sqlScript
     */
    protected abstract void handleSingleScript(SqlScript sqlScript);
}
