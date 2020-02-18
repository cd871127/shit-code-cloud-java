package com.shit_code.cloud.consul.config;

import com.shit_code.cloud.consul.Application;
import com.shit_code.cloud.consul.config.loader.LocalConfigLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class ConfigUploaderTest {

    @Resource
    private ConfigUploader consulManager;

    @Resource
    private LocalConfigLoader localConfigProperties;

    @Test
    public void uploadConfig() {
        consulManager.uploadConfig(localConfigProperties.loadConfig());
    }
}