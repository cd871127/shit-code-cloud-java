package com.shit_code.cloud.lib.springboot.database.sql.process;

        import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/3/4
 **/
@FunctionalInterface
public interface SqlProcessor {
    /**
     * 处理sql
     *
     * @param sql
     * @param args
     * @return
     */
    String process(String sql, List<Integer> args);
}
