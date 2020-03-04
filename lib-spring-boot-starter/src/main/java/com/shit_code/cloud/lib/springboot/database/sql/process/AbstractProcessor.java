package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;

/**
 * @author Anthony Chen
 * @date 2020/2/27
 **/
@Data
@Slf4j
@NoArgsConstructor
public abstract class AbstractProcessor implements SqlProcessor {

    private SqlProcessor nextSqlProcessor;

    public AbstractProcessor(SqlProcessor sqlProcessor) {
        setNextSqlProcessor(sqlProcessor);
    }

    /**
     * @param sqlScript
     */
    @Override
    public final void process(SqlScript sqlScript) {
        log.debug("sql脚本: {}", sqlScript);
        //第一个节点 直接把模板放到里面
        if (CollectionUtils.isEmpty(sqlScript.getSqlList())) {
            log.debug("首次处理: {}", sqlScript);
            sqlScript.setSqlList(Collections.singletonList(sqlScript.getTemplate()));
        }
        //是否支持
        if (support(sqlScript)) {
            log.debug("开始处理: {}", sqlScript);
            doProcess(sqlScript);
        }
        //进入下一个节点
        if (getNextSqlProcessor() != null) {
            log.debug("执行下一个处理器: {}", sqlScript);
            nextSqlProcessor.process(sqlScript);
        }
    }

    /**
     * 处理逻辑
     *
     * @param sqlScript
     */
    protected abstract void doProcess(SqlScript sqlScript);

    /**
     * 是否支持
     *
     * @param sqlScript
     * @return
     */
    protected abstract boolean support(SqlScript sqlScript);

}
