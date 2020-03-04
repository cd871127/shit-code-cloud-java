package com.shit_code.cloud.consul.config.loader;

import com.shit_code.cloud.consul.config.ConfigContent;
import com.shit_code.cloud.consul.config.ConfigProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
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

    private ConfigProperties.LocalConfig localConfig;


    @Override
    public List<ConfigContent> loadConfig() {
        final PathMatcher pathMatcher = buildPathMatcher();
        try {
            return walkDir(pathMatcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 创建文件匹配规则
     *
     * @return
     */
    private PathMatcher buildPathMatcher() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("glob:**/*.{");
        localConfig.getSuffix().forEach((suffix) -> {
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
    private List<ConfigContent> walkDir(PathMatcher pathMatcher) throws IOException {
        final Path configPath = Paths.get(localConfig.getPath());
        List<ConfigContent> configContents = new ArrayList<>();
        Files.walkFileTree(configPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(file)) {
                    String config = Files.lines(file).collect(Collectors.joining("\n"));
                    configContents.add(new ConfigContent(config, configPath.relativize(file)));
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return configContents;
    }

}
