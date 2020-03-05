package com.shit_code.cloud.consul.config.loader;

import com.shit_code.cloud.consul.config.configuration.Config;
import com.shit_code.cloud.consul.config.configuration.ConfigProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class LocalConfigLoader implements ConfigLoader {

    private ConfigProperties configProperties;

    /**
     * 加载配置
     *
     * @return
     */
    @Override
    public List<Config> loadConfig() {
        final PathMatcher pathMatcher = buildPathMatcher();
        try {
            return walkDir(pathMatcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 创建文件匹配规则
     *
     * @return
     */
    private PathMatcher buildPathMatcher() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("glob:**/*.{");
        configProperties.getLocal().getSuffix().forEach((suffix) -> {
            stringBuffer.append(suffix);
            stringBuffer.append(",");
        });
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append("}");
        String glob = stringBuffer.toString();
        return FileSystems.getDefault().getPathMatcher(glob);
    }

    /**
     * 遍历目录
     *
     * @param pathMatcher
     * @return
     * @throws IOException
     */
    private List<Config> walkDir(PathMatcher pathMatcher) throws IOException {
        final Path configPath = Paths.get(configProperties.getLocal().getPath() + "/" + configProperties.getEnv());
        final Path rootPath = Paths.get(configProperties.getLocal().getPath());
        List<Config> configContents = new ArrayList<>();
        Files.walkFileTree(configPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(file)) {
                    String configValue = Files.lines(file).collect(Collectors.joining("\n"));
                    Config config = Config.ConfigFactory.createConfig(rootPath.relativize(file), configValue);
                    if (StringUtils.isNotEmpty(configValue) && config != null) {
                        configContents.add(config);
                    } else {
                        log.warn("配置文件{},读取失败", file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return configContents;
    }

}
