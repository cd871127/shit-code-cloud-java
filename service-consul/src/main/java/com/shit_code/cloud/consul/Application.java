package com.shit_code.cloud.consul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
@SpringCloudApplication
@Slf4j
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
