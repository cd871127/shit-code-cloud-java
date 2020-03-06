package com.shit_code.cloud.consul.config;

import com.shit_code.cloud.consul.Application;
import com.shit_code.cloud.lib.springboot.consul.ConsulLock;
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

    @Resource
    private ConsulLock consulLock;

    @Test
    public void test1() {
        System.out.println(1);
        boolean test = consulLock.tryLock("test", "1232", 3,3000);
        System.out.println(test);
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


    @Test
    public void write() {
        configManager.writeBack();
    }
}