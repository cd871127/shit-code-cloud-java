package com.shit_code.cloud.lib.springboot.database.sql.handler;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;
import com.shit_code.cloud.lib.springboot.database.sql.SqlScriptType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Anthony Chen
 * @date 2020/2/25
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class SimpleScriptHandler extends AbstractSqlScriptHandler implements InitializingBean {

    @Override
    protected List<SqlScript> from() {
        String sourceLocation = sqlScriptProperties.getSourceLocation();
        try {
            List<SqlScript> scripts = new LinkedList<>();
            Files.walkFileTree(Paths.get(sourceLocation), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isRegularFile(file)) {
                        SqlScript sqlScript = new SqlScript();
                        sqlScript.setOriginContent(Files.lines(file).reduce((s1, s2) -> s1 + "\n" + s2).orElse(""));
                        scripts.add(sqlScript);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            return scripts;
        } catch (IOException e) {
            log.warn("遍历脚本目录异常");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    protected void to(List<SqlScript> scripts) {
        String targetLocation = sqlScriptProperties.getTargetLocation();


    }

    @Override
    protected List<SqlScript> doHandle(List<SqlScript> scripts) {
        Map<SqlScriptType, List<SqlScript>> groupingScript = scripts.stream().collect(Collectors.groupingBy(SqlScript::getType));
        for (SqlScriptType sqlScriptType : SqlScriptType.values()) {
            sqlScriptType.getSqlScriptGenerator().execute(groupingScript.getOrDefault(sqlScriptType, Collections.emptyList()));
        }
        return scripts;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        handle();
    }
}
