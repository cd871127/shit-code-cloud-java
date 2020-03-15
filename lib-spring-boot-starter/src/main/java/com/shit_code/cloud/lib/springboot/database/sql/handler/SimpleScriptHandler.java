package com.shit_code.cloud.lib.springboot.database.sql.handler;

import com.shit_code.cloud.lib.core.exception.ShitCodeException;
import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
        File targetDir = Paths.get(targetLocation).toFile();
        if (!targetDir.exists() && !targetDir.mkdirs()) {
            throw new ShitCodeException();
        }
        scripts.parallelStream().filter(sqlScript ->
                StringUtils.isEmpty(sqlScript.getName()) || StringUtils.isEmpty(sqlScript.getVersion()))
                .forEach(sqlScript -> {
                    AtomicInteger subVersion = new AtomicInteger(1);
                    sqlScript.getSqlList().forEach((script) -> {
                        String filePath = targetLocation + File.separator + flywayProperties.getSqlMigrationPrefix()
                                + sqlScript.getVersion()
                                + "." + subVersion.getAndAdd(1)
                                + flywayProperties.getSqlMigrationSeparator()
                                + sqlScript.getName()
                                + sqlScriptProperties.getScriptSuffix();
                        try {
                            Path file = Paths.get(filePath);
                            if (!file.toFile().exists()) {
                                log.debug("Write sql script: {}", filePath);
                                Files.write(Paths.get(filePath), script.getBytes(StandardCharsets.UTF_8));
                            } else {
                                log.debug("File {} already exists", filePath);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                });

    }

    @Override
    protected List<SqlScript> doHandle(List<SqlScript> scripts) {
        scripts.forEach(sqlScript -> processorChain.go(sqlScript));
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
        if (path.startsWith("classpath:")) {
            return getClass().getResource(path.replace("classpath:", "")).getPath();
        } else if (path.startsWith("filesystem:")) {
            return path.replace("filesystem:", "");
        } else {
            return path;
        }
    }

    /**
     * 创建脚本信息
     *
     * @return
     */
    private SqlScript createSqlScript(Path filePath) {
        SqlScript sqlScript = null;
        if (sqlScriptProperties.getScripts() != null) {
            //用文件名获取配置
            sqlScript = sqlScriptProperties.getScripts()
                    .getOrDefault(filePath.getFileName().toString()
                            .replace(sqlScriptProperties.getScriptSuffix(), ""), null);
        }
        if (sqlScript == null) {
            sqlScript = new SqlScript();
        }
        //读取配置
        try {
            sqlScript.setTemplate(Files.lines(filePath).collect(Collectors.joining("\n")));
        } catch (IOException e) {
            sqlScript.setTemplate("");
        }
        return sqlScript;
    }
}
