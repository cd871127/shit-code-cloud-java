package com.shit_code.cloud.consul;

import com.shit_code.cloud.consul.config.ConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
@SpringCloudApplication
@Slf4j
public class Application implements ApplicationRunner {

    @Resource
    private ConfigManager configManager;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
