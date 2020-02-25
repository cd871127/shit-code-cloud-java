package com.shit_code.cloud.lib.springboot.database.sql.generator;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/26
 **/
public interface SqlScriptGenerator {

    /**
     * 解析sql文件
     *
     * @param scripts
     * @return
     */
    void execute(List<SqlScript> scripts);

}
