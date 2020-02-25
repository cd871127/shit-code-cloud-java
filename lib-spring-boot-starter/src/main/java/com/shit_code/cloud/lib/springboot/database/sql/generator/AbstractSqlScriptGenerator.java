package com.shit_code.cloud.lib.springboot.database.sql.generator;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/26
 **/
public abstract class AbstractSqlScriptGenerator implements SqlScriptGenerator {

    @Override
    public void execute(List<SqlScript> scripts) {
        scripts.forEach(this::handleSingleScript);
    }

    /**
     * 处理单个sql
     *
     * @param sqlScript
     */
    protected abstract void handleSingleScript(SqlScript sqlScript);
}
