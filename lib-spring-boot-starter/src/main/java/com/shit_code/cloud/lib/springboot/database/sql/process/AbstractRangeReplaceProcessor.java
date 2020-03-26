package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Anthony Chen
 * @date 2020/3/4
 **/
public abstract class AbstractRangeReplaceProcessor extends AbstractProcessor {

    @Override
    protected void doProcess(SqlScript sqlScript) {
        final var start = startIndex(sqlScript);
        final var end = endIndex(sqlScript);
        var format = format();
        //TODO 并行
        sqlScript.setSqlList(sqlScript.getSqlList().stream().flatMap(
                sql -> IntStream.range(start, end)
                        .mapToObj(index -> sql.replaceAll(placeHolder(), format.format(index)))
        ).collect(Collectors.toList()));
    }

    /**
     * 获取起始范围
     *
     * @param sqlScript
     * @return
     */
    protected abstract int startIndex(SqlScript sqlScript);

    /**
     * 获取终止范围
     *
     * @param sqlScript
     * @return
     */
    protected abstract int endIndex(SqlScript sqlScript);

    /**
     * 格式化器
     *
     * @return
     */
    protected Format format() {
        return new DecimalFormat(pattern());
    }

    /**
     * 是否支持
     *
     * @param sqlScript
     * @return
     */
    @Override
    protected boolean support(SqlScript sqlScript) {
        return sqlScript.getTemplate().contains(replaceFlag());
    }

    /**
     * 替换标记
     *
     * @return
     */
    protected abstract String replaceFlag();

    /**
     * 占位符
     *
     * @return
     */
    protected abstract String placeHolder();

    protected String pattern() {
        return "00";
    }
}
