package com.shit_code.cloud.lib.springboot.database.sql.handler;

import com.shit_code.cloud.lib.springboot.database.sharding.ShardingInfo;
import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;
import com.shit_code.cloud.lib.springboot.database.sql.SqlScriptType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.Location;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Anthony Chen
 * @date 2020/2/25
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class SimpleScriptHandler extends AbstractSqlScriptHandler implements InitializingBean {

    final private String PATH_PREFIX = "filesystem:";

    /**
     * 从目标文件夹读取
     *
     * @return
     */
    @Override
    protected List<SqlScript> from() {
        //解析路径
        String sourceLocation = parseLocation(sqlScriptProperties.getSourceLocation());
        try {
            List<SqlScript> scripts = new LinkedList<>();
            //遍历目录
            Files.walkFileTree(Paths.get(sourceLocation), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (Files.isRegularFile(file)) {
                        scripts.add(createSqlScript(file));
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

    /**
     * 写入到文件夹
     *
     * @param scripts sql脚本
     */
    @Override
    protected void to(List<SqlScript> scripts) {
        String targetLocation = parseLocation(sqlScriptProperties.getTargetLocation());
        scripts.forEach(sqlScript -> {
            AtomicInteger subVersion=new AtomicInteger(1);
            sqlScript.getGeneratedContents().forEach((script) -> {
                String filePath = targetLocation + File.separator + flywayProperties.getSqlMigrationPrefix()
                        + sqlScript.getVersion()
                        + "." + subVersion.getAndAdd(1)
                        + flywayProperties.getSqlMigrationSeparator()
                        + sqlScript.getName()
                        + sqlScriptProperties.getScriptSuffix();
                try {
                    log.debug("Write sql script: {}", filePath);
                    Files.write(Paths.get(filePath), script.getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

    }

    @Override
    protected List<SqlScript> doHandle(List<SqlScript> scripts) {
        Map<SqlScriptType, List<SqlScript>> groupingScript = scripts.stream()
                .collect(Collectors.groupingBy(sqlScript -> {
                    if (sqlScript.getSharding() != null) {
                        return sqlScript.getSharding().getType();
                    }
                    return SqlScriptType.NORMAL;
                }));
        for (SqlScriptType sqlScriptType : SqlScriptType.values()) {
            sqlScriptType.getSqlScriptGenerator().generate(groupingScript.getOrDefault(sqlScriptType, Collections.emptyList()));
        }
        return scripts;
    }

    @Override
    public void afterPropertiesSet() {
        handle();
    }

    /**
     * 解析路径
     *
     * @return
     */
    private String parseLocation(String path) {
        try {
            Location location = new Location(path);
            if (location.isClassPath()) {
                String errInfo = "Target location not support classpath: " + path;
                log.error(errInfo);
                throw new IllegalArgumentException(errInfo);
            }
            return location.getPath();
        } catch (FlywayException e) {
            return path;
        }
    }

    /**
     * 创建脚本信息
     *
     * @return
     */
    private SqlScript createSqlScript(Path filePath) {
        //用文件名获取配置
        SqlScript sqlScript = sqlScriptProperties.getScripts()
                .getOrDefault(filePath.getFileName().toString().replace(sqlScriptProperties.getScriptSuffix(), ""), null);
        if (sqlScript == null) {
            sqlScript = new SqlScript();
            sqlScript.setSharding(new ShardingInfo(SqlScriptType.NORMAL));
        }
        //设置配置
        try {
            sqlScript.setOriginContent(Files.lines(filePath).reduce((s1, s2) -> s1 + "\n" + s2).orElse(""));
        } catch (IOException e) {
            sqlScript.setOriginContent("");
        }
        return sqlScript;
    }
}
