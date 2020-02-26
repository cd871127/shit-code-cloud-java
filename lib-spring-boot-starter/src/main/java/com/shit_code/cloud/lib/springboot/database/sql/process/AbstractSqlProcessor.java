package com.shit_code.cloud.lib.springboot.database.sql.process;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/27
 **/
@Data
@Slf4j
@NoArgsConstructor
public abstract class AbstractSqlProcessor {

    private AbstractSqlProcessor nextSqlProcessor;

    public AbstractSqlProcessor(AbstractSqlProcessor nextSqlProcessor) {
        setNextSqlProcessor(nextSqlProcessor);
    }

    /**
     * 替换sql种的占位符
     *
     * @param sql  sql本身
     * @param args 替换的值
     * @return 新的sql
     */
    public String process(String sql, List<Integer> args) {
        if (CollectionUtils.isEmpty(args)) {
            log.warn("missing args");
            return sql;
        }
        String newSql = sql;
        if (support(sql)) {
            newSql = sql.replaceAll(placeholder(), new DecimalFormat(pattern()).format(args.get(0)));
            args.remove(0);
        }
        if (getNextSqlProcessor() != null) {
            newSql = nextSqlProcessor.process(newSql, args);
        }
        return newSql;
    }

    /**
     * 是否支持
     *
     * @param sql sql本身
     * @return
     */
    protected abstract boolean support(String sql);

    protected abstract String pattern();

    protected abstract String placeholder();

}
