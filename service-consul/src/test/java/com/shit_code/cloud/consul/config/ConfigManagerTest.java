package com.shit_code.cloud.consul.config;

import com.shit_code.cloud.consul.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class ConfigManagerTest {
    @Resource
    private ConfigManager configManager;

    @Test
    public void test1() {
        System.out.println(1);
    }

    @Test
    public void reloadConfig() {
        configManager.reloadConfig();
    }

    @Test
    public void pushConfig() {
        configManager.pushConfig(false);
    }

    @Test
    public void cleanConfig() {
        configManager.cleanConfig();
    }
}