package com.shit_code.cloud.lib.springboot.database.sql.handler;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;
import com.shit_code.cloud.lib.springboot.database.sql.SqlScriptProperties;
import com.shit_code.cloud.lib.springboot.database.sql.process.ProcessorChain;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;

import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/25
 **/
@Data
public abstract class AbstractSqlScriptHandler implements SqlScriptHandler {

    protected SqlScriptProperties sqlScriptProperties;

    protected FlywayProperties flywayProperties;

    protected ProcessorChain processorChain;

    @Override
    public void handle() {
        var scripts = from();
        if (CollectionUtils.isNotEmpty(scripts)) {
            var result = doHandle(scripts);
            to(result);
        }
    }

    /**
     * 获取sql脚本
     *
     * @return
     */
    protected abstract List<SqlScript> from();


    /**
     * 写入处理后的脚本
     *
     * @param scripts sql脚本
     */
    protected abstract void to(List<SqlScript> scripts);


    /**
     * 处理脚本
     *
     * @param scripts 处理前的脚本
     * @return 处理后的脚本
     */
    protected abstract List<SqlScript> doHandle(List<SqlScript> scripts);
}
