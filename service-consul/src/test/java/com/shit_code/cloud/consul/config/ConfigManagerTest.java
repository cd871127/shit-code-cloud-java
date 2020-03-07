package com.shit_code.cloud.consul.config;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.shit_code.cloud.consul.Application;
import com.shit_code.cloud.lib.springboot.consul.ConsulLock;
import com.shit_code.cloud.lib.springboot.redis.RedisLock;
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
    @Resource
    private RedisLock redisLock;
    @Resource
    private ConsulClient consulClient;

    @Test
    public void test1() {
        System.out.println(1);
//        boolean test = consulLock.tryLock("test", "1232", 3,3000);
        boolean test = consulLock.unlock("test1");
        System.out.println(test);
//        test=consulLock.unlock("test", "1232");
//        System.out.println(test);
    }

    @Test
    public void test2() {
        System.out.println(1);
        System.out.println(redisLock.unlock("test1"));
//        System.out.println(redisLock.unlock("test","123"));
    }

    @Test
    public void test3() {
        Response<GetValue> c= consulClient.getKVValue("123");
        System.out.println(1);
//        System.out.println(redisLock.unlock("test","123"));
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